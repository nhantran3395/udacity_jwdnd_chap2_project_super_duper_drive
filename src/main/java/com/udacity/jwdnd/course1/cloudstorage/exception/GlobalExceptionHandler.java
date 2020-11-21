package com.udacity.jwdnd.course1.cloudstorage.exception;

import com.udacity.jwdnd.course1.cloudstorage.util.SetQueryParamsForOpeningAlertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private SetQueryParamsForOpeningAlertUtil setQueryParamsForOpeningAlertUtil;

    @ExceptionHandler(value = StorageException.class)
    public ModelAndView storageExceptionHandler(Exception exception){
        String errorMessage = exception.getMessage();
        ModelMap model = setQueryParamsForOpeningAlertUtil.setQueryParamsForAlertInCaseOfError(true,"error","file","storageFailure",errorMessage);

        return new ModelAndView("redirect:/files",model);
    }

    @ExceptionHandler(value = StorageFileNotFoundException.class)
    public ModelAndView storageFileNotFoundExceptionHandler(Exception exception){
        String errorMessage = exception.getMessage();
        ModelMap model = setQueryParamsForOpeningAlertUtil.setQueryParamsForAlertInCaseOfError(true,"error","file","storageFileNotFound",errorMessage);

        return new ModelAndView("redirect:/files",model);
    }

    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    public ModelAndView maxUploadSizeExceededExceptionHandler(Exception exception){
        String errorMessage = "Maximum upload size exceeded";
        ModelMap model = setQueryParamsForOpeningAlertUtil.setQueryParamsForAlertInCaseOfError(true,"error","file","sizeLimitExceeded",errorMessage);

        return new ModelAndView("redirect:/files",model);
    }

}
