package com.makebono.mavenplayland.test.encrpytion;

import java.security.Key;

import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;

/** 
 * @ClassName: EncryptionTest 
 * @Description: EncryptionTest 
 * @author makebono
 * @date 2018年2月9日 上午11:45:12 
 *  
 */
public class EncryptionTest {
    public static void reference(final String text) {
        final AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128);

        final Key key = aesCipherService.generateNewKey();

        final String encrptText = aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();

        System.out.println(encrptText);

        final String text2 = new String(aesCipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());

        System.out.println(text2);
    }
}
