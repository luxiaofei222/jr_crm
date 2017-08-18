package com.jingren.jing.util;

import org.hyperic.sigar.*;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RuntimeUtil {
	private static Sigar sigar;

	/**
	 * 获取sigar实体
	 */
	public static Sigar getInstance() {
		if (null == sigar) {
			sigar = new Sigar();
		}
		return sigar;
	}

	/**
	 * 1.获取系统信息和jvm虚拟机信息
	 */
	public static List<SigarInfoEntity> getJvmInfos() throws Exception {
		List<SigarInfoEntity> jvmInfoList = new ArrayList<>();

		// 系统环境变量信息map
//		Map<String, String> envInfoMap = System.getenv();
//		jvmInfoList.add(new SigarInfoEntity(envInfoMap.get("USERNAME"), "用户名"));
//		jvmInfoList.add(new SigarInfoEntity(envInfoMap.get("COMPUTERNAME"), "计算机名"));
//		jvmInfoList.add(new SigarInfoEntity(envInfoMap.get("USERDOMAIN"), "计算机域名"));

		// java对ip封装的对象
		InetAddress addr = InetAddress.getLocalHost();
		jvmInfoList.add(new SigarInfoEntity("120.24.88.165", "公网IP"));
		jvmInfoList.add(new SigarInfoEntity(addr.getHostAddress(), "内网IP"));
		jvmInfoList.add(new SigarInfoEntity(addr.getHostName(), "主机名称"));

		Runtime r = Runtime.getRuntime();
		// String.valueOf(r.totalMemory())
		// jvmInfoList.add(new
		// SigarInfoEntity(NumUtil.get_file_lenth(r.totalMemory()), "JVM总内存"));
		// jvmInfoList.add(new
		// SigarInfoEntity(NumUtil.get_file_lenth(r.freeMemory()), "JVM剩余内存"));
		jvmInfoList.add(new SigarInfoEntity(String.valueOf(r.availableProcessors()), "jvm处理器个数"));

		// 系统配置属性
		Properties sysProps = System.getProperties();
		jvmInfoList.add(new SigarInfoEntity(sysProps.getProperty("user.name"), "用户的账户名称"));
		jvmInfoList.add(new SigarInfoEntity(sysProps.getProperty("os.name"), "操作系统"));
		jvmInfoList.add(new SigarInfoEntity(sysProps.getProperty("os.arch"), "操作系统的构架"));
		jvmInfoList.add(new SigarInfoEntity(sysProps.getProperty("os.version"), "操作系统的版本"));
		jvmInfoList.add(new SigarInfoEntity(sysProps.getProperty("java.version"), "Java运行环境版本"));
		jvmInfoList.add(new SigarInfoEntity(sysProps.getProperty("java.vendor"), "Java运行环境供应商"));
		jvmInfoList.add(new SigarInfoEntity(sysProps.getProperty("java.vendor.url"), "Java供应商的URL"));
		// jvmInfoList.add(new
		// SigarInfoEntity(sysProps.getProperty("java.home"), "Java的安装路径"));
		jvmInfoList.add(new SigarInfoEntity(sysProps.getProperty("java.vm.specification.version"), "Java虚拟机规范版本"));
		jvmInfoList.add(new SigarInfoEntity(sysProps.getProperty("java.vm.specification.vendor"), "Java虚拟机规范供应商"));
		jvmInfoList.add(new SigarInfoEntity(sysProps.getProperty("java.vm.specification.name"), "Java虚拟机规范名称"));
		jvmInfoList.add(new SigarInfoEntity(sysProps.getProperty("java.vm.version"), "Java虚拟机实现版本"));
		jvmInfoList.add(new SigarInfoEntity(sysProps.getProperty("java.vm.vendor"), "Java虚拟机实现供应商"));
		jvmInfoList.add(new SigarInfoEntity(sysProps.getProperty("java.vm.name"), "Java虚拟机实现名称"));
		jvmInfoList.add(new SigarInfoEntity(sysProps.getProperty("java.specification.version"), "Java运行时环境规范版本"));
		jvmInfoList.add(new SigarInfoEntity(sysProps.getProperty("java.specification.vendor"), "Java运行时环境规范供应商"));
		jvmInfoList.add(new SigarInfoEntity(sysProps.getProperty("java.specification.name"), "Java虚拟机规范名称"));
		jvmInfoList.add(new SigarInfoEntity(sysProps.getProperty("java.class.version"), "Java类格式版本号"));
		// jvmInfoList.add(new
		// SigarInfoEntity(sysProps.getProperty("java.class.path"),
		// "Java的类路径"));
		// jvmInfoList.add(new
		// SigarInfoEntity(sysProps.getProperty("java.library.path"),
		// "加载库时搜索的路径列表"));
		// jvmInfoList.add(new
		// SigarInfoEntity(sysProps.getProperty("java.io.tmpdir"),
		// "默认的临时文件路径"));
		// jvmInfoList.add(new
		// SigarInfoEntity(sysProps.getProperty("java.ext.dirs"),
		// "一个或多个扩展目录的路径"));

		// jvmInfoList.add(new
		// SigarInfoEntity(sysProps.getProperty("file.separator"), "文件分隔符"));
		// jvmInfoList.add(new
		// SigarInfoEntity(sysProps.getProperty("path.separator"), "路径分隔符"));
		// jvmInfoList.add(new
		// SigarInfoEntity(sysProps.getProperty("line.separator"), "行分隔符"));

		// jvmInfoList.add(new
		// SigarInfoEntity(sysProps.getProperty("user.home"), "用户的主目录"));
		// jvmInfoList.add(new SigarInfoEntity(sysProps.getProperty("user.dir"),
		// "用户的当前工作目录"));

		return jvmInfoList;
	}

	/**
	 * 2.获取cpu信息
	 */
	public static List<SigarInfoEntity> getCpuInfos() throws SigarException {
		List<SigarInfoEntity> cpuInfoList = new ArrayList<>();
		CpuInfo[] cpuInfos = getInstance().getCpuInfoList();
		for (int i = 0; i < cpuInfos.length; i++) {
			CpuInfo cpuInfo = cpuInfos[i];
			cpuInfoList.add(new SigarInfoEntity(String.valueOf(i), "第" + i + "个CPU信息"));
			cpuInfoList.add(new SigarInfoEntity(String.valueOf(cpuInfo.getMhz()), "CPU的总量MHz" + i));
			cpuInfoList.add(new SigarInfoEntity(cpuInfo.getVendor(), "获得CPU的供应商" + i));
			cpuInfoList.add(new SigarInfoEntity(cpuInfo.getModel(), "获得CPU的类别" + i));
			cpuInfoList.add(new SigarInfoEntity(String.valueOf(cpuInfo.getCacheSize()), "缓冲存储器数量" + i));
		}

		CpuPerc[] cpuPercs = getInstance().getCpuPercList();
		for (int i = 0; i < cpuPercs.length; i++) {
			CpuPerc cpuPerc = cpuPercs[i];
			cpuInfoList.add(new SigarInfoEntity(String.valueOf(i), "第" + i + "个CPU片段"));
			cpuInfoList.add(new SigarInfoEntity(String.valueOf(cpuPerc.getUser()), "CPU用户使用率" + i));
			cpuInfoList.add(new SigarInfoEntity(String.valueOf(cpuPerc.getSys()), "CPU系统使用率" + i));
			cpuInfoList.add(new SigarInfoEntity(String.valueOf(cpuPerc.getWait()), "CPU当前等待率" + i));
			cpuInfoList.add(new SigarInfoEntity(String.valueOf(cpuPerc.getNice()), "CPU当前错误率" + i));
			cpuInfoList.add(new SigarInfoEntity(String.valueOf(cpuPerc.getIdle()), "CPU当前空闲率" + i));
			cpuInfoList.add(new SigarInfoEntity(String.valueOf(cpuPerc.getCombined()), "CPU总的使用率" + i));
		}
		return cpuInfoList;
	}

	/**
	* @Title: RuntimeUtil.java 
	* @Package com.jingren.jing.util 
	* @Description: TODO cpu平均使用率
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月31日 上午8:50:12 
	* @version 网校+CRM系统 V1.0
	 */
	public static List<SigarInfoEntity> getCpuInfosUsepoint() throws SigarException {
		List<SigarInfoEntity> cpuInfoList = new ArrayList<>();
		CpuPerc[] cpuPercs = getInstance().getCpuPercList();
		double usershiyonglv=0;
		double kongxian=0;
		double zongshiyonglv=0;
		java.text.DecimalFormat  df  =new java.text.DecimalFormat("0.00");  
		int j=cpuPercs.length;
		for (int i = 0; i < j; i++) {
			CpuPerc cpuPerc = cpuPercs[i];
			usershiyonglv+=cpuPerc.getUser();
			kongxian+=cpuPerc.getIdle();
			zongshiyonglv+=cpuPerc.getCombined();
		}
		cpuInfoList.add(new SigarInfoEntity(String.valueOf(df.format(usershiyonglv/j)),"cupuse"));
		cpuInfoList.add(new SigarInfoEntity(String.valueOf(df.format(kongxian/j)), "kongxian"));
		cpuInfoList.add(new SigarInfoEntity(String.valueOf(df.format(zongshiyonglv/j)),"zongshiyong"));
		return cpuInfoList;
	}
	
	/**
	 * @Title: RuntimeUtil.java
	 * @Package com.jingren.jing.util
	 * @Description: TODO 获取jvm内存信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年7月28日 下午6:02:38
	 * @version 网校+CRM系统 V1.0
	 */
	public static List<SigarInfoEntity> getMemoryInfos_jvmneicun() throws SigarException {
		List<SigarInfoEntity> jvmInfoList = new ArrayList<>();
		Runtime r = Runtime.getRuntime();
		jvmInfoList.add(new SigarInfoEntity(NumUtil.get_file_lenth(r.totalMemory()-r.freeMemory()), "yishiyong"));
		jvmInfoList.add(new SigarInfoEntity(String.valueOf((r.totalMemory()-r.freeMemory())/(1024*1024)), "yishiyongnum"));
		jvmInfoList.add(new SigarInfoEntity(String.valueOf(r.freeMemory()/(1024*1024)), "shengyunum"));
		jvmInfoList.add(new SigarInfoEntity(NumUtil.get_file_lenth(r.freeMemory()), "shengyu"));
		return jvmInfoList;
	}

	/**
	 * 3.获取内存信息
	 */
	public static List<SigarInfoEntity> getMemoryInfos_neicun() throws SigarException {
		List<SigarInfoEntity> memoryInfoList = new ArrayList<>();
		Mem mem = getInstance().getMem();
		//memoryInfoList.add(new SigarInfoEntity(mem.getTotal() / 1024L + "K av", "内存总量"));
		memoryInfoList.add(new SigarInfoEntity(mem.getUsed() / 1024L + "KB", "yiyong"));
		memoryInfoList.add(new SigarInfoEntity(String.valueOf(mem.getUsed()/(1024*1024)), "yishiyongnum"));
		memoryInfoList.add(new SigarInfoEntity(String.valueOf(mem.getFree()/(1024*1024)), "shengyunum"));
		memoryInfoList.add(new SigarInfoEntity(mem.getFree() / 1024L + "KB", "shengyu"));
		return memoryInfoList;
	}

	/**
	 * @Title: RuntimeUtil.java
	 * @Package com.jingren.jing.util
	 * @Description: TODO 交换区内存
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年7月28日 下午5:49:28
	 * @version 网校+CRM系统 V1.0
	 */
	public static List<SigarInfoEntity> getMemoryInfos_jiaohuanqu() throws SigarException {
		List<SigarInfoEntity> memoryInfoList = new ArrayList<>();
		Swap swap = getInstance().getSwap();
		//memoryInfoList.add(new SigarInfoEntity(swap.getTotal() / 1024L + "K av", "交换区总量"));
//		memoryInfoList.add(new SigarInfoEntity(NumUtil.get_file_lenth(swap.getUsed()), "yishiyong"));
//		memoryInfoList.add(new SigarInfoEntity(NumUtil.get_file_lenth(swap.getFree()), "shengyu"));
		memoryInfoList.add(new SigarInfoEntity(String.valueOf(swap.getUsed() /(1024)), "yishiyongnum"));
		memoryInfoList.add(new SigarInfoEntity(String.valueOf(swap.getFree() /(1024)), "shengyunum"));
		return memoryInfoList;
	}

	/**
	 * 4.获取操作系统信息
	 */
	public static List<SigarInfoEntity> getOsInfos() {
		List<SigarInfoEntity> osInfoList = new ArrayList<>();
		OperatingSystem os = OperatingSystem.getInstance();
		osInfoList.add(new SigarInfoEntity(os.getArch(), "操作系统"));
		osInfoList.add(new SigarInfoEntity(os.getCpuEndian(), "操作系统CpuEndian()"));
		osInfoList.add(new SigarInfoEntity(os.getDataModel(), "操作系统DataModel()"));
		osInfoList.add(new SigarInfoEntity(os.getDescription(), "操作系统的描述"));
		osInfoList.add(new SigarInfoEntity(os.getVendor(), "操作系统的供应商"));
		osInfoList.add(new SigarInfoEntity(os.getVendorCodeName(), "操作系统的供应商编号"));
		osInfoList.add(new SigarInfoEntity(os.getVendorName(), "操作系统的供应商名称"));
		osInfoList.add(new SigarInfoEntity(os.getVendorVersion(), "操作系统供应商类型"));
		osInfoList.add(new SigarInfoEntity(os.getVersion(), "操作系统的版本号"));
		return osInfoList;
	}

	/**
	 * 5.获取文件信息
	 */
	public static List<SigarInfoEntity> getFileInfos() throws SigarException {
		List<SigarInfoEntity> fileInfoList = new ArrayList<>();
		FileSystem fslist[] = getInstance().getFileSystemList();
		long usershiyong=0;
		long shengyu=0;
		java.text.DecimalFormat  df  =new java.text.DecimalFormat("0.00");  
		for (int i = 0; i < fslist.length; i++) {
			FileSystem fs = fslist[i];
			// fileInfoList.add(new SigarInfoEntity(i + "", "分区的盘符号" + i));
			// fileInfoList.add(new SigarInfoEntity(fs.getDevName(), "盘符名称" +
			// i));
			// fileInfoList.add(new SigarInfoEntity(fs.getDirName(), "盘符路径" +
			// i));
			// fileInfoList.add(new SigarInfoEntity(fs.getFlags() + "", "盘符标志" +
			// i));
			// fileInfoList.add(new SigarInfoEntity(fs.getSysTypeName(),
			// "盘符类型(FAT32,NTFS)" + i));
			// fileInfoList.add(new SigarInfoEntity(fs.getTypeName(), "盘符类型名" +
			// i));
			// fileInfoList.add(new SigarInfoEntity(fs.getType() + "",
			// "盘符文件系统类型" + i));
			FileSystemUsage usage;
			try {
				usage = getInstance().getFileSystemUsage(fs.getDirName());
			} catch (SigarException e) {// 当fileSystem.getType()为5时会出现该异常——此时文件系统类型为光驱
				// System.out.println("----------------------------------------------------------------------------------");
				// System.out.println(fs.getDirName());
				// 经测试，会输出个G:\ 我表示是相当的不解。后来发现是我笔记本的光驱，吐血。。。这也行。怪不得原来这代码是OK的
				// 估计是台式机，还是没光驱的台式机。
				continue;
			}
			// 下面单独这行代码就会报错：org.hyperic.sigar.SigarException: The device is not
			// ready.
			// usage = getInstance().getFileSystemUsage(fs.getDirName());
			switch (fs.getType()) {
			case 0: // TYPE_UNKNOWN ：未知
				break;
			case 1: // TYPE_NONE
				break;
			case 2: // TYPE_LOCAL_DISK : 本地硬盘
				//fileInfoList.add(new SigarInfoEntity(usage.getTotal() + "KB", "文件系统总大小" + fs.getDevName()));
				usershiyong+=usage.getUsed();
				shengyu+=usage.getFree();
				//fileInfoList.add(new SigarInfoEntity(usage.getFree() + "KB", "文件系统剩余大小" + fs.getDevName()));
				//fileInfoList.add(new SigarInfoEntity(usage.getAvail() + "KB", "文件系统可用大小" + fs.getDevName()));
				//fileInfoList.add(new SigarInfoEntity(usage.getUsed() + "KB", "文件系统已经使用量" + fs.getDevName()));
				//fileInfoList.add(new SigarInfoEntity(usage.getUsePercent() * 100D + "%", "文件系统资源的利用率" + fs.getDevName()));
				break;
			case 3:// TYPE_NETWORK ：网络
				break;
			case 4:// TYPE_RAM_DISK ：闪存
				break;
			case 5:// TYPE_CDROM ：光驱
				break;
			case 6:// TYPE_SWAP ：页面交换
				break;
			}
			//fileInfoList.add(new SigarInfoEntity(usage.getDiskReads() + "", fs.getDevName() + "读出"));
			//fileInfoList.add(new SigarInfoEntity(usage.getDiskWrites() + "", fs.getDevName() + "写入"));
		}
		fileInfoList.add(new SigarInfoEntity(df.format(usershiyong/(1024*1024)),"yiyong"));
		fileInfoList.add(new SigarInfoEntity(df.format(shengyu/(1024*1024)),"shengyu"));
		return fileInfoList;
	}

	/**
	 * 6.获取网络信息
	 */
	public static List<SigarInfoEntity> getNetInfos() throws SigarException {
		List<SigarInfoEntity> netInfoList = new ArrayList<>();
		String nIfNames[] = getInstance().getNetInterfaceList();
		for (int i = 0; i < nIfNames.length; i++) {
			String name = nIfNames[i];
			NetInterfaceConfig nIfConfig = getInstance().getNetInterfaceConfig(name);
			netInfoList.add(new SigarInfoEntity(name, "网络设备名" + i));
			netInfoList.add(new SigarInfoEntity(nIfConfig.getAddress(), "IP地址" + i));
			netInfoList.add(new SigarInfoEntity(nIfConfig.getNetmask(), "子网掩码" + i));

			if ((nIfConfig.getFlags() & 1L) <= 0L) {
				System.out.println("getNetInterfaceStat not exist");
				continue;
			}
			NetInterfaceStat nIfStat = getInstance().getNetInterfaceStat(name);
			netInfoList.add(new SigarInfoEntity(nIfStat.getRxPackets() + "", "接收的总包裹数" + i));
			netInfoList.add(new SigarInfoEntity(nIfStat.getTxPackets() + "", "发送的总包裹数" + i));
			netInfoList.add(new SigarInfoEntity(nIfStat.getRxBytes() + "", "接收到的总字节数" + i));
			netInfoList.add(new SigarInfoEntity(nIfStat.getTxBytes() + "", "发送的总字节数" + i));
			netInfoList.add(new SigarInfoEntity(nIfStat.getRxErrors() + "", "接收到的错误包数" + i));
			netInfoList.add(new SigarInfoEntity(nIfStat.getTxErrors() + "", "发送数据包时的错误数" + i));
			netInfoList.add(new SigarInfoEntity(nIfStat.getRxDropped() + "", "接收时丢弃的包数" + i));
			netInfoList.add(new SigarInfoEntity(nIfStat.getTxDropped() + "", "发送时丢弃的包数" + i));
		}
		return netInfoList;
	}

	public static void main(String[] args) {

		// Sigar信息集合
		List<SigarInfoEntity> sigarInfos = new ArrayList<>();
		try {
			// 1.获取系统信息和jvm虚拟机信息
			// sigarInfos.addAll(getJvmInfos());
			// 2.获取cpu信息
			 //sigarInfos.addAll(getCpuInfos());
			// 3.获取内存信息
			 sigarInfos.addAll(getMemoryInfos_neicun());
			// 4.获取操作系统信息
			// sigarInfos.addAll(getOsInfos());
			// 5.获取文件信息
			//sigarInfos.addAll(getFileInfos());
			// 6.获取网络信息
			// sigarInfos.addAll(getNetInfos());

			StringBuilder sigarStringBuffer = new StringBuilder();
			for (SigarInfoEntity sigarInfo : sigarInfos) {
				sigarStringBuffer.append(sigarInfo.getName()).append(":").append(sigarInfo.getValue()).append("\r\n");
			}
			System.out.println(sigarStringBuffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
