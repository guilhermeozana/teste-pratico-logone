package com.teste.pratico.converter;

import com.teste.pratico.bean.SolicitanteBean;
import com.teste.pratico.domain.dto.SolicitanteDTO;
import com.teste.pratico.domain.model.Solicitante;
import com.teste.pratico.service.SolicitanteService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Component
@FacesConverter(value = "solicitanteConverter", forClass = Solicitante.class)
@NoArgsConstructor
public class SolicitanteConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        SolicitanteBean solicitanteBean = (SolicitanteBean) FacesContext.getCurrentInstance().getELContext().getELResolver().getValue(context.getELContext(), null, "solicitanteBean");

        return solicitanteBean.getSolicitanteService().obterSolicitantePorId(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }

        return String.valueOf(((Solicitante) value).getId());
    }
}

