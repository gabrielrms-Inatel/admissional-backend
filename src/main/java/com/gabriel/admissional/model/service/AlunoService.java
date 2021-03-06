package com.gabriel.admissional.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel.admissional.model.dao.AlunoDAO;
import com.gabriel.admissional.model.entity.Aluno;

@Service
@Transactional(readOnly = true)
public class AlunoService {
	
	// Service responsável por gerenciar a entidade Aluno
	
	@Autowired
	private AlunoDAO dao;
	
	@Transactional(readOnly = false)
	public void criarAluno(Aluno aluno) {
		try {
			dao.save(aluno);
			System.out.println("Aluno criado!");
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao criar o aluno." + e.toString());
		}
	}
	
	public List<Aluno> buscarTodos() {
		return dao.findAll();
	}
	
	// Métodos para conversão do retorno do banco para a entidade Aluno
	public List<Aluno> buscarPorTurma(Long id){
		Aluno aluno = null;
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		List<Object[]> aux = dao.findByTurma(id);
		
		for (Object[] obj : aux) {
			aluno = new Aluno();
			
			aluno.setId(Long.parseLong(obj[0].toString()));
			aluno.definirMatricula(Integer.parseInt(obj[1].toString()));
			aluno.definirNome(obj[2].toString());
			
			alunos.add(aluno);
		}
		return alunos;
	}
	
	public List<Aluno> buscarPorSemTurma() {
		Aluno aluno = null;
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		List<Object[]> aux = dao.findByNoTurma();
		
		for (Object[] obj : aux) {
			aluno = new Aluno();
			
			aluno.setId(Long.parseLong(obj[0].toString()));
			aluno.definirMatricula(Integer.parseInt(obj[1].toString()));
			aluno.definirNome(obj[2].toString());
			
			alunos.add(aluno);
		}
		return alunos;
	}
}
