package edu.uabc.app.service;

import java.util.List;

import edu.uabc.app.model.TipoArchivo;

public interface ITiposArchivosService {

	List<TipoArchivo> buscarTodas();
	TipoArchivo buscarPorId(int id_tipo_archivo);
}
