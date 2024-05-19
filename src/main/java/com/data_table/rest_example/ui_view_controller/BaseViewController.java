package com.data_table.rest_example.ui_view_controller;

import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import org.primefaces.PrimeFaces;

public abstract class BaseViewController {

    @Inject
    FacesContext facesContext;

    protected FacesContext getFacesContext() {
        return facesContext;
    }

    protected Object getSessionParameter(String key) {
        return facesContext.getExternalContext().getSessionMap().get(key);
    }

    protected void setSessionParameter(String key, Object value) {
        facesContext.getExternalContext().getSessionMap().put(key, value);
    }
}
