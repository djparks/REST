package org.rest.sec.client.marshall;

import java.util.List;

import org.rest.common.client.marshall.IMarshaller;
import org.rest.sec.model.Principal;
import org.rest.sec.model.Privilege;
import org.rest.sec.model.Role;
import org.rest.sec.model.dto.User;
import org.springframework.http.MediaType;

import com.google.common.base.Preconditions;
import com.thoughtworks.xstream.XStream;

public final class XStreamMarshaller implements IMarshaller {

    private XStream xstream;

    public XStreamMarshaller() {
        super();

        xstream = new XStream();
        xstream.autodetectAnnotations(true);
        xstream.processAnnotations(User.class);
        xstream.processAnnotations(Principal.class);
        xstream.processAnnotations(Role.class);
        xstream.processAnnotations(Privilege.class);
    }

    // API

    @Override
    public final <T> String encode(final T entity) {
        Preconditions.checkNotNull(entity);
        return xstream.toXML(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final <T> T decode(final String entityAsXML, final Class<T> clazz) {
        Preconditions.checkNotNull(entityAsXML);
        return (T) xstream.fromXML(entityAsXML);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> decodeList(final String entityAsXML, final Class<T> clazz) {
        return this.decode(entityAsXML, List.class);
    }

    @Override
    public final String getMime() {
        return MediaType.APPLICATION_XML.toString();
    }

}
