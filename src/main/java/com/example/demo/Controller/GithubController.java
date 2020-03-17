package com.example.demo.Controller;

import java.io.IOException;
import java.util.List;

import org.eclipse.jgit.api.errors.CheckoutConflictException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dao.GitDao;

@RestController
@RequestMapping("/git")
public class GithubController {

	@Autowired
	GitDao gitdao;
	
	
	
	@GetMapping("/clone")
	public String cloneRepository() 
	{
		try {
			gitdao.cloneRepository();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			System.out.println("catch block");
			e.printStackTrace();
		}	
		return "success";
	}
	
	@GetMapping("/getAllBranches")
	public List getAllBranches() throws IOException, GitAPIException
	{
		return gitdao.getAllBranches();
	}
	
	@GetMapping("/createBranch")
	public String createBranch()
	{
		gitdao.createBranch();
		return "success";
	}
	
	@GetMapping("/pushBranch")
	public String pushBranch() throws IOException
	{
		try {
			gitdao.pushBranch();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			System.out.println("catch block");
			e.printStackTrace();
		}
		return "success";
	}
	
	@DeleteMapping("/deleteBranch")
	public String deleteBranch()
	{
		try {
			gitdao.deleteBranch();
		} catch (IOException | GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
	@GetMapping("/getRepositories")
	public List getRepositories() throws IOException
	{
		return gitdao.getRepositories();
		
	}
	
	@GetMapping("/checkout")
	public String checkout() throws IOException
	{
		return gitdao.chekout();
	}
	@GetMapping("/checkOutBranch")
	public String checkOutBranch() throws Exception {
		return gitdao.checkOutBranch();
	}
}
