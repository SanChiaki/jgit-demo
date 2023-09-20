package com.example.jgit.test;

import java.io.File;
// import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

public class GitClient {
    private Git git;

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
        git.push().call();
    }
}