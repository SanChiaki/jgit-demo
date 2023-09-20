package com.example.jgit.test;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

public class Jgit {
    public void cloneRepository(String url, String path)
            throws InvalidRemoteException, TransportException, GitAPIException {
        Git.cloneRepository()
                .setURI("git@github.com:SanChiaki/todo.git")
                .setDirectory(new File("./tmp"))
                .call();
    }
    public void commitRepository(String message) throws GitAPIException {
        try (Git git = Git.open(new File("./tmp"))) {
            git.add().addFilepattern(".").call();
            git.commit().setMessage(message).call();
        } catch (IOException e) {
            // Auto-generated catch block
            e.printStackTrace();
        }
    }
}
