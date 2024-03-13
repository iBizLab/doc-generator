package cn.ibizlab.codegen.groovy.util;

import java.math.BigInteger;
import java.rmi.server.UID;
import java.security.MessageDigest;
import java.util.UUID;
import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 键值功能对象，此组件相关实现是为了兼容iBiz5.0功能
 * @author lionlau
 *
 */
public class KeyValueUtils {

	/**
	 * 临时数据主键前缀
	 */
	public final static String TEMPKEYPREFIX = "SRFTEMPKEY:";
	
	private static class Snowflake {
	    private static final int UNUSED_BITS = 1; // Sign bit, Unused (always set to 0)
	    public static final int EPOCH_BITS = 41;
	    public static final int NODE_ID_BITS = 10;
	    public static final int SEQUENCE_BITS = 12;

	    public static final long maxNodeId = (1L << NODE_ID_BITS) - 1;
	    public static final long maxSequence = (1L << SEQUENCE_BITS) - 1;

	    // Custom Epoch (January 1, 2015 Midnight UTC = 2015-01-01T00:00:00Z)
	    //private static final long DEFAULT_CUSTOM_EPOCH = 1420070400000L;
	    private static final long DEFAULT_CUSTOM_EPOCH = 1288834974657L;
	 //   private final long twepoch = 1288834974657L;
	    
	    private final long nodeId;
	    private final long customEpoch;

	    private volatile long lastTimestamp = -1L;
	    private volatile long sequence = 0L;

	    // Create Snowflake with a nodeId and custom epoch
	    public Snowflake(long nodeId, long customEpoch) {
	        if(nodeId < 0 || nodeId > maxNodeId) {
	            throw new IllegalArgumentException(String.format("NodeId must be between %d and %d", 0, maxNodeId));
	        }
	        this.nodeId = nodeId;
	        this.customEpoch = customEpoch;
	    }

	    // Create Snowflake with a nodeId
	    public Snowflake(long nodeId) {
	        this(nodeId, DEFAULT_CUSTOM_EPOCH);
	    }

	    // Let Snowflake generate a nodeId
	    public Snowflake() {
	        this.nodeId = createNodeId();
	        this.customEpoch = DEFAULT_CUSTOM_EPOCH;
	    }

	    public synchronized long nextId() {
	        long currentTimestamp = timestamp();

	        if(currentTimestamp < lastTimestamp) {
	            throw new IllegalStateException("Invalid System Clock!");
	        }

	        if (currentTimestamp == lastTimestamp) {
	            sequence = (sequence + 1) & maxSequence;
	            if(sequence == 0) {
	                // Sequence Exhausted, wait till next millisecond.
	                currentTimestamp = waitNextMillis(currentTimestamp);
	            }
	        } else {
	            // reset sequence to start with zero for the next millisecond
	            sequence = 0;
	        }

	        lastTimestamp = currentTimestamp;

	        long id = currentTimestamp << (NODE_ID_BITS + SEQUENCE_BITS)
	                | (nodeId << SEQUENCE_BITS)
	                | sequence;

	        return id;
	    }


	    // Get current timestamp in milliseconds, adjust for the custom epoch.
	    private long timestamp() {
	        return Instant.now().toEpochMilli() - customEpoch;
	    }

	    // Block and wait till next millisecond
	    private long waitNextMillis(long currentTimestamp) {
	        while (currentTimestamp == lastTimestamp) {
	            currentTimestamp = timestamp();
	        }
	        return currentTimestamp;
	    }

	    public long createNodeId() {
	        long nodeId;
	        try {
	            StringBuilder sb = new StringBuilder();
	            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
	            while (networkInterfaces.hasMoreElements()) {
	                NetworkInterface networkInterface = networkInterfaces.nextElement();
	                byte[] mac = networkInterface.getHardwareAddress();
	                if (mac != null) {
	                    for(byte macPort: mac) {
	                        sb.append(String.format("%02X", macPort));
	                    }
	                }
	            }
	            nodeId = sb.toString().hashCode();
	        } catch (Exception ex) {
	            nodeId = (new SecureRandom().nextInt());
	        }
	        nodeId = nodeId & Snowflake.maxNodeId;
	        return nodeId;
	    }

	    public long[] parse(long id) {
	        long maskNodeId = ((1L << NODE_ID_BITS) - 1) << SEQUENCE_BITS;
	        long maskSequence = (1L << SEQUENCE_BITS) - 1;

	        long timestamp = (id >> (NODE_ID_BITS + SEQUENCE_BITS)) + customEpoch;
	        long nodeId = (id & maskNodeId) >> SEQUENCE_BITS;
	        long sequence = id & maskSequence;

	        return new long[]{timestamp, nodeId, sequence};
	    }

	    @Override
	    public String toString() {
	        return "Snowflake Settings [EPOCH_BITS=" + EPOCH_BITS + ", NODE_ID_BITS=" + NODE_ID_BITS
	                + ", SEQUENCE_BITS=" + SEQUENCE_BITS + ", CUSTOM_EPOCH=" + customEpoch
	                + ", NodeId=" + nodeId + "]";
	    }
	}
	
	
	private static Map<String, Snowflake> snowflakeMap = new HashMap<>();
	private static long workerId = 0l;
	
	static {
		Snowflake snowflake = new Snowflake();
		workerId = snowflake.createNodeId();
	}
	
	final public static long getWorkerId() {
		return workerId;
	}
	
	final public static void setWorkerId(long workerId) {
		KeyValueUtils.workerId = workerId;
	}
	
    public static long genNumberId(String strType) {
    	Snowflake snowflake = snowflakeMap.get(strType);
    	if(snowflake == null) {
    		synchronized (snowflakeMap) {
    			snowflake = snowflakeMap.get(strType);
    			if(snowflake == null) {
    				snowflake = new Snowflake(getWorkerId());
    				snowflakeMap.put(strType, snowflake);
    			}
			}
    	}
    	return snowflake.nextId();
    }
    
	/**
	 * 新建一个UID
	 * 
	 * @return
	 */
	final public static String genGuid() {
		UID uid = new UID();
		String strId = "uid_" + uid.toString();
		strId = strId.replace(":", "");
		strId = strId.replace("-", "");
		return strId;
	}

	/**
	 * 新建一个GUID
	 * 
	 * @return
	 */
	final public static String genGuidEx() {
		UUID idOne = UUID.randomUUID();
		return idOne.toString().toUpperCase();
	}
	
	/**
	 * 产生UUID字符串
	 * 
	 * @return
	 */
	final public static String genUUIDString() {
		UUID idOne = UUID.randomUUID();
		return idOne.toString().toUpperCase();
	}
	
	
	/**
	 * 产生UUID大整数
	 * @return
	 */
	public static BigInteger genUUIDNumber()
	{
		UUID uuid = UUID.randomUUID();
		BigInteger uuidAsBigInteger = new BigInteger(uuid.toString().replace("-", ""), 16);
		return uuidAsBigInteger;
	}
	

	final private static String convertToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9))
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}

	/**
	 * 获取指定文本的MD5字符串
	 * 
	 * @param text
	 * @return
	 */
	final public static String genMD5(String text) {
		try {
			MessageDigest md;
			md = MessageDigest.getInstance("MD5");
			byte[] md5hash = new byte[32];
			md.update(text.getBytes("iso-8859-1"), 0, text.length());
			md5hash = md.digest();
			return convertToHex(md5hash);
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * 获取指定文本的MD5字符串
	 * 
	 * @param text
	 * @return
	 */
	final public static String genMD5Ex(String text) {
		try {
			MessageDigest md;
			md = MessageDigest.getInstance("MD5");
			byte[] md5hash = new byte[32];
			md.update(text.getBytes("utf-8"));
			md5hash = md.digest();
			return convertToHex(md5hash);
		} catch (Exception ex) {
			return "";
		}
	}

	
	/**
	 * 产生唯一标识
	 * 
	 * @return
	 */
	final public static String genUniqueId() {
		return genMD5Ex(UUID.randomUUID().toString());
	}
	
	
	
	/**
	 * 产生唯一标识
	 * 
	 * @param strSrc1
	 * @return
	 */
	final public static String genUniqueId(Object strSrc1) {
		if(strSrc1 instanceof String) {
			return genMD5Ex((String)strSrc1);
		}
		else {
			return genMD5Ex(String.format("%1$s", strSrc1));
		}
	}

	/**
	 * 产生唯一标识
	 * 
	 * @param strSrc1
	 * @param strSrc2
	 * @return
	 */
	final public static String genUniqueId(Object strSrc1, Object strSrc2) {
		return genMD5Ex(String.format("%1$s||%2$s", strSrc1, strSrc2));
	}

	/**
	 * 产生唯一标识
	 * 
	 * @param strSrc1
	 * @param strSrc2
	 * @param strSrc3
	 * @return
	 */
	final public static String genUniqueId(Object strSrc1, Object strSrc2, Object strSrc3) {
		return genMD5Ex(String.format("%1$s||%2$s||%3$s", strSrc1, strSrc2, strSrc3));
	}

	/**
	 * 产生唯一标识
	 * 
	 * @param strSrc1
	 * @param strSrc2
	 * @param strSrc3
	 * @param strSrc4
	 * @return
	 */
	final public static String genUniqueId(Object strSrc1, Object strSrc2, Object strSrc3, Object strSrc4) {
		return genMD5Ex(String.format("%1$s||%2$s||%3$s||%4$s", strSrc1, strSrc2, strSrc3, strSrc4));
	}

	/**
	 * 产生唯一标识
	 * 
	 * @param strSrc1
	 * @param strSrc2
	 * @param strSrc3
	 * @param strSrc4
	 * @param strSrc5
	 * @return
	 */
	final public static String genUniqueId(Object strSrc1, Object strSrc2, Object strSrc3, Object strSrc4, Object strSrc5) {
		return genMD5Ex(String.format("%1$s||%2$s||%3$s||%4$s||%5$s", strSrc1, strSrc2, strSrc3, strSrc4, strSrc5));
	}

	/**
	 * 产生唯一标识
	 * 
	 * @param strSrc1
	 * @param strSrc2
	 * @param strSrc3
	 * @param strSrc4
	 * @param strSrc5
	 * @param strSrc6
	 * @return
	 */
	final public static String genUniqueId(Object strSrc1, Object strSrc2, Object strSrc3, Object strSrc4, Object strSrc5, Object strSrc6) {
		return genMD5Ex(String.format("%1$s||%2$s||%3$s||%4$s||%5$s||%6$s", strSrc1, strSrc2, strSrc3, strSrc4, strSrc5, strSrc6));
	}

	/**
	 * 是否为临时主键
	 * 
	 * @param strKeyValue
	 * @return
	 */
	final public static boolean isTempKey(String strKeyValue) {
		return strKeyValue.indexOf(TEMPKEYPREFIX) == 0;
	}
	
	
	/**
	 * 计算唯一标记
	 * @param values
	 * @return
	 */
	final public static String genUniqueId(Object[] values) {
		switch(values.length) {
		case 1:
			return genMD5Ex(String.format("%1$s", values[0]));
		case 2:
			return genMD5Ex(String.format("%1$s||%2$s", values[0], values[1]));
		case 3:
			return genMD5Ex(String.format("%1$s||%2$s||%3$s", values[0], values[1], values[2]));
		case 4:
			return genMD5Ex(String.format("%1$s||%2$s||%3$s||%4$s", values[0], values[1], values[2], values[3]));
		case 5:
			return genMD5Ex(String.format("%1$s||%2$s||%3$s||%4$s||%5$s", values[0], values[1], values[2], values[3], values[4]));
		case 6:
			return genMD5Ex(String.format("%1$s||%2$s||%3$s||%4$s||%5$s||%6$s", values[0], values[1], values[2], values[3], values[4], values[5]));
		case 7:
			return genMD5Ex(String.format("%1$s||%2$s||%3$s||%4$s||%5$s||%6$s||%7$s", values[0], values[1], values[2], values[3], values[4], values[5], values[6]));
		case 8:
			return genMD5Ex(String.format("%1$s||%2$s||%3$s||%4$s||%5$s||%6$s||%7$s||%8$s", values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7]));
		default:
			throw new RuntimeException("无法计算唯一标记");
		}
	}
	
}
