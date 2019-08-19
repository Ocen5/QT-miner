package com.example.ablminer;

 class ServerException extends Exception {

	ServerException(String result)
	{
		System.err.println(result);
	}

}
