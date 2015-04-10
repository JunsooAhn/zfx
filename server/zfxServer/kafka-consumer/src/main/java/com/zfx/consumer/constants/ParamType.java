package com.moneylocker.consumer.constants;

public enum ParamType {
	// TEXT and TIMESTAMP are the cassandra types, included here (alongside STRING AND DATE)
	// in order to make it easier to write the config information (see src/main/resources/logSchema.properties)
	LONG, INT, STRING, DATE, TEXT, TIMESTAMP
}
