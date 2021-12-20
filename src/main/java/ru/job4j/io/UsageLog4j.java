package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean bool = false;
        char ch = 'q';
        int num = 10;
        double numDouble = 1d;
        long numLong = 1L;
        byte numByte = 1;
        short sh = 1;
        float fl = 1f;
        LOG.debug("Переменные: bool:{}, ch:{}, num:{}, numDouble:{}, numLong:{}, numByte:{}, sh:{}, fl:{}", bool, ch, num, numDouble, numLong, numByte, sh, fl);
    }
}