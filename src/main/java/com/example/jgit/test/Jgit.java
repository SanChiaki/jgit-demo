package com.example.jgit.test;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
// https://segmentfault.com/a/1190000043387122
public class Jgit {
    public void cloneRepository(String url, String path)
            throws InvalidRemoteException, TransportException, GitAPIException {
        Git.cloneRepository()
                .setURI(url)
                .setDirectory(new File(path))
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
