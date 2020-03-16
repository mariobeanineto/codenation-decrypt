package com.codenation.decrypt;

import com.codenation.decrypt.service.DecryptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Java6Assertions.assertThat;

@SpringBootTest
class DecryptApplicationTests {

    @Autowired
    private DecryptService decryptService;

    @Test
    void testRightDecrypt() {
        final String encrypted = "oznoviyj, xmdkojbmvadv, epgdj, xznvm1234.,!@#%$";
        final String decrypted = decryptService.shiftEncryptedString(encrypted, 21);
        assertThat(decrypted).isEqualTo("testando, criptografia, julio, cesar1234.,!@#%$");
    }

	@Test
	void testSha1Encryption() throws Exception {
		final String decrypted = "vamos, aprender, criptografia.%$#@123";
		final String encrypted = decryptService.encryptInSha1(decrypted);
		assertThat(encrypted).isEqualTo("09fb39e8e5a238ee6b21df4338424b63d945b785");
	}

}
