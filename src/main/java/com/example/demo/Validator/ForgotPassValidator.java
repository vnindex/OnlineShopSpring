package com.example.demo.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.demo.Dao.UserDao;
import com.example.demo.Entity.User;
import com.example.demo.Model.ForgotPassword;

@Component
public class ForgotPassValidator implements Validator {
	
	@Autowired
	private UserDao dao;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz == ForgotPassword.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ForgotPassword entity = (ForgotPassword) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotBlank.forgotPassword.email");
	
		if(!errors.hasFieldErrors()) {
			User user = dao.findByEmail(entity.getEmail());
		
			if(user == null) {
				errors.rejectValue("email", "NotFind.forgotPassword.email");
			}
		}
	}

}
