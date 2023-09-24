package com.example.jgit.test;

import java.io.File;
// import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class GitClient {
    private Git git;
    String username = System.getenv("USERNAME");
    String password = System.getenv("PASSWORD");

    private UsernamePasswordCredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider(username,
            password);

    public GitClient(String url, String path) throws InvalidRemoteException, TransportException, GitAPIException {
        git = Git.cloneRepository()
                .setURI(url)
                .setDirectory(new File(path))
                .call();
    }

    public void commitRepository(String message) throws GitAPIException {
        git.add().addFilepattern(".").call();
        git.commit().setMessage(message).call();
    }

    public void pushRepository() throws GitAPIException {
        git.push().setCredentialsProvider(credentialsProvider).call();
    }

    // void setCredentialsProvider(UsernamePasswordCredentialsProvider
    // credentialsProvider) throws InvalidRemoteException, TransportException,
    // GitAPIException {
    // git.push().setCredentialsProvider(credentialsProvider).call();
    // }
}