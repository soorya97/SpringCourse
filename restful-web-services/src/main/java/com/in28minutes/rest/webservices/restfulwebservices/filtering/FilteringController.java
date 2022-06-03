package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	//send ony field1, field2
	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean(){
		SomeBean someBean = new SomeBean("Value 01", "Value 02", "Value 03");
		return getMappingJacksonValue(someBean, "field1", "field2");
	}

	//send only field2, field3
	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveListOfSomeBean(){
		List<SomeBean> someBeanList = Arrays.asList(new SomeBean("Value 01", "Value 02", "Value 03"), new SomeBean("Value 11", "Value 12", "Value 13"));
		return getMappingJacksonValue(someBeanList, "field2", "field3");
	}

	private MappingJacksonValue getMappingJacksonValue(Object bean, String... fields){
		SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", simpleBeanPropertyFilter);

		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(bean);
		mappingJacksonValue.setFilters(filterProvider);

		return mappingJacksonValue;
	}
}
