package com.example.demo.Dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.CreateBranchCommand.SetupUpstreamMode;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.CannotDeleteCurrentBranchException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NotMergedException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.stereotype.Repository;


@Repository
public class GitDao {

	public void cloneRepository() throws InvalidRemoteException, TransportException, GitAPIException {
		// TODO Auto-generated method stub
		Git git = Git.cloneRepository().setURI("https://github.com/Goutham-Harshith/Angular-Practise")
				.setDirectory(new File("C:\\Users\\Winfo\\Documents\\GitHub\\Demo1")).call();
	}

	public void createBranch() {
		File src = new File("C:\\Users\\Winfo\\Documents\\GitHub\\Demo1");
		String branch = "newBranch";
		CreateBranchCommand bcc = null;
		CheckoutCommand checkout = null;
		Git git;

		try {
			org.eclipse.jgit.lib.Repository repo = new FileRepositoryBuilder().readEnvironment().findGitDir(src)
					.build();
			git = new Git(repo);

			bcc = git.branchCreate();
			checkout = git.checkout();
		} catch (IOException e) {
			System.out.println("catch bloack 1");
		}

		try {
			bcc.setName(branch).setUpstreamMode(SetupUpstreamMode.SET_UPSTREAM).setStartPoint("origin/" + "master")
					.setForce(true).call();

			checkout.setName(branch);
			checkout.call();
		} catch (Exception e) {
			System.out.println("catch block 2");
			e.printStackTrace();
		}

	}

	public void pushBranch() throws InvalidRemoteException, TransportException, GitAPIException, IOException {
		CredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider("Goutham-Harshith",
				"199714sjrishi");
		File src = new File("C:\\Users\\Winfo\\Documents\\GitHub\\Demo1");
		org.eclipse.jgit.lib.Repository repo = new FileRepositoryBuilder().readEnvironment().findGitDir(src).build();
		Git git = new Git(repo);
		PushCommand pushCommand = git.push();
		pushCommand.setRemote("origin").setCredentialsProvider(credentialsProvider);
//		pushCommand.setRefSpecs(new RefSpec("release_2_0_2:release_2_0_2"));
		pushCommand.call();

	}

	public void deleteBranch()
			throws IOException, NotMergedException, CannotDeleteCurrentBranchException, GitAPIException {

		CredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider("Goutham-Harshith",
				"199714sjrishi");
		File src = new File("C:\\Users\\Winfo\\Documents\\GitHub\\Demo1");
		org.eclipse.jgit.lib.Repository repo = new FileRepositoryBuilder().readEnvironment().findGitDir(src).build();
		Git git = new Git(repo);

		// delete branch 'branchToDelete' locally

		File file = new File("C:\\Users\\Winfo\\Documents\\GitHub\\Demo1\\.git\\refs\\heads\\newBranch");

		if (file.delete()) {
			System.out.println("File deleted successfully");
		} else {
			System.out.println("Failed to delete the file");
		}

		// delete branch 'branchToDelete' locally (1)
//	    	git.branchDelete().setBranchNames("refs/heads/newBranch").call();

		// delete branch 'branchToDelete' locally (2)
//		DeleteBranchCommand command = git.branchDelete();
//		command.setBranchNames("newBranch3");
//		command.setForce(true);
//		try {
//		    List<String> deletedBranches = new ArrayList<String>();
//		deletedBranches = command.call();
//		System.out.println(deletedBranches.toString());
//		} catch (GitAPIException e) {
//			e.printStackTrace();
//			System.out.println("catch block");
//		}

		// delete branch 'branchToDelete' on remote 'origin'

		RefSpec refSpec = new RefSpec().setSource(null).setDestination("refs/heads/newBranch");
		git.push().setRefSpecs(refSpec).setRemote("origin").setCredentialsProvider(credentialsProvider).call();
	}

	public List getAllBranches() throws IOException, GitAPIException {
		CredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider("Goutham-Harshith",
				"199714sjrishi");

		Collection<Ref> refs;
		List<String> branches = new ArrayList<String>();
		try {
			refs = Git.lsRemoteRepository().setHeads(true).setRemote("https://github.com/winfo-analytics/WDAS")
					.setCredentialsProvider(credentialsProvider).call();
			for (Ref ref : refs) {
				branches.add(ref.getName().substring(ref.getName().lastIndexOf("/") + 1, ref.getName().length()));
			}
			Collections.sort(branches);
		} catch (InvalidRemoteException e) {

			e.printStackTrace();
		} catch (TransportException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
		return branches;
	}

	public List getRepositories() throws IOException {
		File src = new File("C:\\Users\\Winfo\\Documents\\GitHub\\WDAS");
		org.eclipse.jgit.lib.Repository repo = new FileRepositoryBuilder().readEnvironment().findGitDir(src).build();
		Git git = new Git(repo);
		// String branchName = "newBranch";
		String branchName = "pipelineWorkingBranch";
		try {
			Ref ref = git.checkout().setName("branchName2").call();

		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public String chekout() throws IOException {

		File src = new File("C:\\Users\\Winfo\\Documents\\GitHub\\WDAS");
		org.eclipse.jgit.lib.Repository repo = new FileRepositoryBuilder().readEnvironment().findGitDir(src).build();
		Git git = new Git(repo);
		// String branchName = "newBranch";
		String branchName = "pipelineWorkingBranch";
		try {
			Ref ref = git.checkout().setName("branchName2").call();

		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "branch changed";
	}

	public String checkOutBranch() throws NoHeadException, GitAPIException, IOException {
		
		Git.cloneRepository()
		  .setURI("C:\\Users\\Winfo\\Documents\\GitHub\\WDAS") // your git url
		  .setDirectory(new File("C:\\Users\\Winfo\\Documents\\GitHub\\Data")) 
		  .setBranchesToClone(Arrays.asList("refs/heads/branchName2"))// give ur branch name
		  .setBranch("refs/heads/branchName2")
		  .call();
		
		return "success";
	}

}
