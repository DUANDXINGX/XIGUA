package com.dd;

import java.security.MessageDigest;

public class Short {
	
	private final static String[] hexDigits = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};

    public static String[] shortUrl(String url, String key) {
        // Ҫʹ������ URL ���ַ�
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"
        };
        // �Դ�����ַ���� MD5 ����
        String hex = (encodeByMD5(key + url));
        String[] resUrl = new String[4];
        //�õ� 4��������ַ���
        for (int i = 0; i < 4; i++) {
            // �Ѽ����ַ����� 8 λһ�� 16 ������ 0x3FFFFFFF ����λ������
            String sTempSubString = hex.substring(i * 8, i * 8 + 8);
            // ������Ҫʹ�� long ����ת������Ϊ Integer .parseInt() ֻ�ܴ��� 31 λ , ��λΪ����λ , ������� long �����Խ��
            long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
            String outChars = "";
            //ѭ�����ÿ��6λ���ַ���
            for (int j = 0; j < 6; j++) {
                // �ѵõ���ֵ�� 0x0000003D ����λ�����㣬ȡ���ַ����� chars ����(������Ҫ��chars����ĳ���   �Է��±������ע�����Ϊ0)
                long index = 0x0000003D & lHexLong;
                // ��ȡ�õ��ַ����
                outChars += chars[(int) index];
                // ÿ��ѭ����λ���� 5 λ
                lHexLong = lHexLong >> 5;
            }
            // ���ַ��������Ӧ�������������
            resUrl[i] = outChars;
        }
        return resUrl;
    }
    
    /**���ַ�������MD5����*/
    private static String encodeByMD5(String originString){
        if (originString!=null) {
            try {
                //��������ָ���㷨���Ƶ���ϢժҪ
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                //ʹ��ָ�����ֽ������ժҪ���������£�Ȼ�����ժҪ����
                byte[] results = md5.digest(originString.getBytes());
                //���õ����ֽ��������ַ�������
                return byteArrayToHexString(results);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    private static String byteArrayToHexString(byte[] b){
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b){
        int n = b;
        if(n<0)
            n=256+n;
        int d1 = n/16;
        int d2 = n%16;
        return hexDigits[d1] + hexDigits[d2];
    }
}
