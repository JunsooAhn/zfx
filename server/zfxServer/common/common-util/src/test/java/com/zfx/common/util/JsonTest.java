package com.zfx.common.util;

import junit.framework.Assert;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class JsonTest {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testDecode() {
		Student student = new Student();
		student.setName("jack");

		Assert.assertTrue(JSON.parseObject(JSON.toJSONString(student), Student.class) instanceof Student);

		Class resultType = Student.class;
		Assert.assertTrue(JSON.parseObject(JSON.toJSONString(student), resultType) instanceof Student);

		Object result = JSON.parseObject(JSON.toJSONString(student), resultType);
		Assert.assertEquals("jack", ((Student) result).getName());
	}

	@Test
	public void testJacksonEnumDecode() throws Exception {
		Student student = new Student();
		student.setName("jack");
		student.setStudentType(StudentType.B);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationConfig.Feature.WRITE_ENUMS_USING_INDEX, true);
		String jsonStr = objectMapper.writeValueAsString(student);
		student = objectMapper.readValue(jsonStr, Student.class);
		Assert.assertEquals(student.getStudentType(), StudentType.B);
	}

	@Test
	public void testJSONUtilEnumDecode() {
		Student student = new Student();
		student.setName("jack");
		student.setStudentType(StudentType.C);
		String jsonStr = JSONUtil.toJSONString(student);
		student = JSONUtil.parseObject(jsonStr, Student.class);
		Assert.assertEquals(student.getStudentType(), StudentType.C);
	}

	private static class Student {

		private String name;

		private StudentType studentType;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public StudentType getStudentType() {
			return studentType;
		}

		public void setStudentType(StudentType studentType) {
			this.studentType = studentType;
		}
	}

	public static enum StudentType {
		A, B, C;
	}
}
