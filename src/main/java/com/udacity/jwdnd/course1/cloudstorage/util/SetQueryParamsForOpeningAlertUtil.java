package com.udacity.jwdnd.course1.cloudstorage.util;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

@Component
public final class SetQueryParamsForOpeningAlertUtil {

    public final ModelMap setQueryParamsForAlert(boolean isAlertToBeOpened, String alertType, String alertForResource, String alertContent){
        ModelMap model = new ModelMap();

        model.addAttribute("isAlertToBeOpened",isAlertToBeOpened);
        model.addAttribute("alertType",alertType);
        model.addAttribute("alertForResource",alertForResource);
        model.addAttribute("alertContent",alertContent);

        return model;
    }

    //set up query params for opening alert in case of error: add alertErrorMessage as argument
    public final ModelMap setQueryParamsForAlertInCaseOfError(boolean isAlertToBeOpened, String alertType, String alertForResource, String alertContent, String alertErrorMessage){
        ModelMap model = new ModelMap();

        model.addAttribute("isAlertToBeOpened",isAlertToBeOpened);
        model.addAttribute("alertType",alertType);
        model.addAttribute("alertForResource",alertForResource);
        model.addAttribute("alertContent",alertContent);
        model.addAttribute("alertErrorMessage",alertErrorMessage);

        return model;
    }
}
