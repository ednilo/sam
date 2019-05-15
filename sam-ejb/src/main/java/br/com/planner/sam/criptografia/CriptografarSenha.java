package br.com.planner.sam.criptografia;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CriptografarSenha {

	public static void main(String[] args) {
		System.out.println(gerarHashMD5("12345"));
	}

	public static String gerarHashMD5(String conteudo) {
		byte[] b;
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			md.reset();
			b = md.digest(conteudo.getBytes());

			return new BigInteger(1, b).toString(16);
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
	}

}
