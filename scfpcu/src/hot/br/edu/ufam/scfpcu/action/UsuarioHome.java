package br.edu.ufam.scfpcu.action;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;

import br.edu.ufam.scfpcu.model.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.security.Identity;

import br.edu.ufam.scfpcu.action.CargoHome;
import br.edu.ufam.scfpcu.action.StringToMd5;

@Name("usuarioHome")
public class UsuarioHome extends EntityHome<Usuario> {

	@In(create = true)
	CargoHome cargoHome;
	
	@In(create = true)
	Identity identity;
	
	private boolean editaSenha;
	Usuario usuarioLogado = null;
	
//	public boolean isEditaSenha() {
//		System.out.println("isEditaSenha???");
//	Usuario usuario = getUsuarioByLogin();
//	if(usuario != null){
//		if(this.getInstance() == usuario)
//			editaSenha = true;
//		else
//			editaSenha = false;
//	}else
//		editaSenha = false;
//	
//	return editaSenha;
//}

	public Usuario getUsuarioLogado() {
		this.usuarioLogado = getUsuarioByLogin();
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public void setEditaSenha(boolean editaSenha) {
		this.editaSenha = editaSenha;
	}

	public void setUsuarioIdServidor(Integer id) {
		setId(id);
	}

	public Integer getUsuarioIdServidor() {
		return (Integer) getId();
	}

	@Override
	protected Usuario createInstance() {
		Usuario usuario = new Usuario();
		return usuario;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		Cargo cargo = cargoHome.getDefinedInstance();
		if (cargo != null) {
			getInstance().setCargo(cargo);
		}
	}

	public boolean isWired() {
		if (getInstance().getCargo() == null)
			return false;
		return true;
	}

	public Usuario getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}
	
	private Usuario getUsuarioByLogin(){
		Usuario usuario = null;
		if(identity.getUsername() != null){
			usuario = (Usuario) super.getEntityManager().createQuery("from Usuario usuario where usuario.login = '"+identity.getUsername()+"'" ).getSingleResult();
		}
		return usuario;
	}
	
	
	@Override
	public String persist(){
		System.out.println("persistindo/.....");
		System.out.println("this.getInstance().getLogin()::::"+this.getInstance().getLogin());
		List<Usuario>  usuarioEmail = (List<Usuario>) super.getEntityManager().createQuery("from Usuario where email = '"+this.getInstance().getEmail()+"'" ).getResultList();
		if(!usuarioEmail.isEmpty()){
			super.getFacesMessages().addToControl("txLoginUsuario",
					FacesMessage.SEVERITY_ERROR, "J� existe um usu�rio com o mesmo e-mail, por favor escolha outro e-mail");
			return "";
		}
		
		List<Usuario>  usuarioLogin = (List<Usuario>) super.getEntityManager().createQuery("from Usuario where login = '"+this.getInstance().getLogin()+"'" ).getResultList();
		if(!usuarioLogin.isEmpty()){
			super.getFacesMessages().addToControl("txLoginUsuario",
					FacesMessage.SEVERITY_ERROR, "J� existe um usu�rio com o mesmo login, por favor escolha outro login");
			return "";
		}
		
		List<Usuario>  usuarioSiape = (List<Usuario>) super.getEntityManager().createQuery("from Usuario where siape = '"+this.getInstance().getSiape()+"'" ).getResultList();
		if(!usuarioSiape.isEmpty()){
			super.getFacesMessages().addToControl("txLoginUsuario",
					FacesMessage.SEVERITY_ERROR, "J� existe um usu�rio com o mesmo SIAPE");
			return "";
		}
		
		
		String result = "";
		this.getInstance().setSenha(StringToMd5.md5(this.getInstance().getSenha()));
		
		try {
			result =  super.persist();
		} catch (Exception e) {
			super.getFacesMessages().add("N�o foi poss�vel fazer o cadastro.");
		}
		
		return result;
	}
	
	@Override
	public String update(){
		System.out.println("Updateeee");
		System.out.println("this.getInstance().getNome():::"+this.getInstance().getNome());
		String result = "";
		this.getInstance().setSenha(StringToMd5.md5(this.getInstance().getSenha()));
		try {
			result = super.update();
		} catch (Exception e) {
			super.getFacesMessages().add("N�o foi poss�vel atualizar registro.");
			System.out.println("Erro::"+e);
		}
		return result;
	}
}
