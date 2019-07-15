package be.retrovideo.retrovideo.sessions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
@SessionScope
public class Mandje implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Set<Long> ids = new LinkedHashSet<>();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public void voegToe(long id){
        ids.add(id);
    }

    public Set<Long> getIds() {
        return ids;
    }

    public void verwijder (Long id){
        ids.remove(id);
    }
    public boolean bevat(long id) {
        return ids.contains(id);
    }

    public int aantalFilmsInMandje(){
        return ids.size();
    }
}
