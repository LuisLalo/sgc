package pruebasjparepo;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.uabc.app.model.UsuarioConsulta;
import edu.uabc.app.repository.UsuariosConsultaRepository;

public class AppFindByAll {

	public static void main(String[] args) {
		// = new Usuario();
		//usuario.setNum_empleado(2);
	/*	usuario.setNombres("Jesus");
		usuario.setApellidos("Gama");
		usuario.setCorreo("j@uabc.edu.mx");
		usuario.setContrasena("1234");
		usuario.setEstatus("Activo");
		usuario.setPuesto_id(1);
		usuario.setRol_id(1);
*/		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		UsuariosConsultaRepository repo = context.getBean("usuariosRepository", UsuariosConsultaRepository.class);
		
		List<UsuarioConsulta> lista = repo.findAll();
		for (UsuarioConsulta u : lista)
			System.out.println(u);

		
		context.close();
	}
}
