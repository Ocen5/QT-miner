package com.example.serversocket;

public class ServerException extends Exception {

	public ServerException(String result)
	{
		System.err.println(result);
	}

}