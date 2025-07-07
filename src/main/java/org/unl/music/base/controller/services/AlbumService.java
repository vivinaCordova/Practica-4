package org.unl.music.base.controller.services;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.unl.music.base.controller.dao.dao_models.DaoAlbum;
import org.unl.music.base.models.Album;
import com.helger.commons.annotation.Nonempty;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotEmpty;

public class AlbumService {
    private DaoAlbum db;
    public AlbumService(){
        db = new DaoAlbum();
    }

    public void createALbum(@NotEmpty String nombre, @Nonempty Integer id_banda,@NonNull Date fecha) throws Exception {
        if(nombre.trim().length() > 0 && id_banda.toString().length() > 0 && fecha.toString().length() > 0) {
            db.getObj().setNombre(nombre);
            db.getObj().setId_banda(id_banda);
            db.getObj().setFecha(fecha);
            if(!db.save())
                throw new  Exception("No se pudo guardar los datos del Album");
        }
    }

    public List<Album> listAllAlbum(){
        return Arrays.asList(db.listAll().toArray());
    }
    
}
