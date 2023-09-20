package com.example.jgit.test;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

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
        GitClient gitClient = new GitClient("https://github.com/username/repo.git", "/path/to/local/repo");
        try {

            // Make changes to the repository
            // ...

            // Commit changes
            gitClient.commitRepository("Commit message");

            // Push changes
            gitClient.pushRepository();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }
}
