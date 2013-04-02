/**************************************************
 * Copyright (c) 2013.
 * 文件名称: ReleaseIoResoureUtil.java
 * 摘　　要: 释放打开的io资源工具类
 *
 * 当前版本: 1.0
 * 作　　者: 宋晓灵
 * 完成日期: 2013-4-2
 **************************************************/
package com.dpf.util;

import java.io.*;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class ReleaseIoResoureUtil {
	// private static Logger
	// log=Logger.getLogger(ReleaseIoResoureUtil.class.getName());
	/**
	 * @param in
	 *            -输入流InputStream 释放打开的单个输入流
	 */
	public static void closeInputStream(Logger logger, InputStream in) {
		if (in != null) {
			try {
				in.close();
				logger.debug("关闭输入流成功");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("关闭输入流失败", e);

			}
		}
	}

	/**
	 * @param inArray
	 *            -输入流InputStream集合 释放打开的多个输入流
	 */
	public static void closeInputStreamSet(Logger logger, InputStream[] inArray) {
		if (inArray != null) {
			for (InputStream in : inArray) {
				if (in != null) {
					try {
						in.close();
						logger.debug("关闭输入流成功");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						logger.error("关闭输入流失败", e);

					}
				}
			}
		}
	}

	/**
	 * 
	 * @param out
	 *            -输出流OutputStream 释放打开的单个输出流
	 */
	public static void closeOutputStream(Logger logger, OutputStream out) {
		if (out != null) {
			try {
				out.close();
				logger.debug("关闭输出流成功");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("关闭输出流失败", e);

			}
		}
	}

	/**
	 * 
	 * @param outArray
	 *            -输出流OutputStream集合 释放打开的多个输出流
	 */
	public static void closeOutputStreamSet(Logger logger,
			OutputStream[] outArray) {
		if (outArray != null) {
			for (OutputStream out : outArray) {
				if (out != null) {
					try {
						out.close();
						logger.debug("关闭输出流成功");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						logger.error("关闭输出流失败", e);

					}
				}
			}
		}
	}

	/**
	 * 
	 * @param in
	 *            -输入流InputStream
	 * @param out
	 *            -输出流OutputStream 关闭打开的输入流和输出流
	 */
	public static void closeInAndOutSream(Logger logger, InputStream in,
			OutputStream out) {
		if (in != null) {
			try {
				in.close();
				logger.debug("关闭输入流成功");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("关闭输入流失败", e);

			}
		}
		if (out != null) {
			try {
				out.close();
				logger.debug("关闭输出流成功");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("关闭输出流失败", e);

			}
		}
	}

	/**
	 * 
	 * @param inArray
	 *            -输入流InputStream集合
	 * @param outArray
	 *            -输出流OutputStream集合 关闭打开的输入流和输出流集合
	 */
	public static void closeInAndOutSreamSet(Logger logger,
			InputStream[] inArray, OutputStream[] outArray) {
		if (inArray != null) {
			for (InputStream in : inArray) {
				if (in != null) {
					try {
						in.close();
						logger.debug("关闭输入流成功");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						logger.error("关闭输入流失败", e);

					}
				}
			}
		}
		if (outArray != null) {
			for (OutputStream out : outArray) {
				if (out != null) {
					try {
						out.close();
						logger.debug("关闭输出流成功");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						logger.error("关闭输出流失败", e);

					}
				}
			}
		}
	}

	/**
	 * 
	 * @param in
	 *            -扫描器Scanner 关闭扫描器
	 */
	public static void closeScanner(Logger logger, Scanner in) {
		if (in != null) {
			try {
				in.close();
				logger.debug("关闭扫描器成功");
			} catch (Exception e) {
				logger.error("关闭扫描器失败", e);
			}
		}
	}

	/**
	 * 
	 * @param inArray
	 *            -扫描器Scanner集合 关闭扫描器集合
	 */
	public static void closeScannerSet(Logger logger, Scanner[] inArray) {
		if (inArray != null) {
			for (Scanner in : inArray) {
				if (in != null) {
					try {
						in.close();
						logger.debug("关闭扫描器成功");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						logger.error("关闭扫描器失败", e);

					}
				}
			}
		}
	}

	/**
	 * 
	 * @param writer
	 *            -写入字符流Writer 关闭写入字符流
	 */
	public static void closeWriter(Logger logger, Writer writer) {
		if (writer != null) {
			try {
				writer.close();
				logger.debug("关闭写入字符流成功");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("关闭写入字符流失败", e);

			}
		}
	}

	/**
	 * 
	 * @param writerArray
	 *            -写入字符流Writer集合 关闭写入字符流集合
	 */
	public static void closeWriterSet(Logger logger, Writer[] writerArray) {
		if (writerArray != null) {
			for (Writer writer : writerArray) {
				if (writer != null) {
					try {
						writer.close();
						logger.debug("关闭写入字符流成功");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						logger.error("关闭写入字符流失败", e);

					}
				}
			}
		}
	}

	/**
	 * 
	 * @param in
	 *            -扫描器Scanner
	 * @param writer
	 *            -写入字符流Writer 关闭扫描器和写入字符流
	 */
	public static void closeScannerAndWriter(Logger logger, Scanner in,
			Writer writer) {
		if (in != null) {
			try {
				in.close();
				logger.debug("关闭扫描器成功");
			} catch (Exception e) {
				logger.error("关闭扫描器失败", e);
			}
		}
		if (writer != null) {
			try {
				writer.close();
				logger.debug("关闭写入字符流成功");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.debug("关闭写入字符流失败", e);

			}
		}
	}

	/**
	 * 
	 * @param inArray
	 *            -扫描器Scanner集合
	 * @param writerArray
	 *            -写入字符流Writer集合 关闭扫描器Scanner集合和写入字符流Writer集合
	 */
	public static void closeScannerAndWriterSet(Logger logger,
			Scanner[] inArray, Writer[] writerArray) {
		if (inArray != null) {
			for (Scanner in : inArray) {
				if (in != null) {
					try {
						in.close();
						logger.debug("关闭扫描器成功");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						logger.error("关闭扫描器失败", e);

					}
				}
			}
		}
		if (writerArray != null) {
			for (Writer writer : writerArray) {
				if (writer != null) {
					try {
						writer.close();
						logger.debug("关闭写入字符流成功");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						logger.error("关闭写入字符流失败", e);

					}
				}
			}
		}
	}
}
