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
}
