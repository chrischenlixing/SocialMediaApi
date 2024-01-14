package com.springboot.blog.springbootblogrestapi;

import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.License;

/**
 * 
 */

public @interface Info {

	String title();

	String description();

	String version();

	Contact contact();

	License license();

}
