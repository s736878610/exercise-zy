package com.zy.juc;

import org.openjdk.jol.info.ClassLayout;

/**
 * JolTest
 * <p>
 * markword内存分布测试
 *
 * @author zhongyuan
 * @since 2023/1/14
 */
public class JolTest {

    /**
     * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     * 0      4        (object header) markWord                  01 00 00 00 (00000001 00000000 00000000 00000000) (1)
     * 4      4        (object header) markWord                  00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     * 8      4        (object header) classPointer              58 0d 00 00 (01011000 00001101 00000000 00000000) (3416)
     * 12     4        (loss due to the next object alignment) 对象对齐所损失的字节
     * Instance size: 16 bytes
     * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
     */
    public static void main(String[] args) throws Exception {

        // Thread.sleep(5000);

        final Object o = new Object();

        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        // synchronized (o) {
        //     System.out.println(ClassLayout.parseInstance(o).toPrintable());
        // }
    }
}
