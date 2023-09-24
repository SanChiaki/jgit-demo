package com.example.jgit.test;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     *
     * @param args The arguments of the program.
     * @throws GitAPIException
     * @throws TransportException
     * @throws InvalidRemoteException
     */
    public static void main(String[] args) throws InvalidRemoteException, TransportException, GitAPIException {
        GitClient gitClient = new GitClient("https://github.com/SanChiaki/langchain-learning.git", "./langchain-learning");

        // Set up credentials
        gitClient.commitRepository("test");
        gitClient.pushRepository();
    }
}
