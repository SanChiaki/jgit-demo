package com.example.jgit.test;

import java.io.File;
// import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.transport.JschConfigSessionFactory;
import org.eclipse.jgit.transport.OpenSshConfig;
import org.eclipse.jgit.transport.SshSessionFactory;
import org.eclipse.jgit.transport.SshTransport;
import org.eclipse.jgit.util.FS;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class GitClient {
    private Git git;

    final static String SSH_KEY_PATH = "/home/oam/.ssh/id_rsa";

    public GitClient(String url, String path) throws InvalidRemoteException, TransportException, GitAPIException {
        SshSessionFactory sshSessionFactory = new JschConfigSessionFactory() {
            @Override
            protected void configure(OpenSshConfig.Host host, Session session) {
                session.setConfig("StrictHostKeyChecking", "no");
            }

            @Override
            protected JSch createDefaultJSch(FS fs) throws JSchException {
                JSch sch = super.createDefaultJSch(fs);
                // keyPath 私钥文件 path
                sch.addIdentity(SSH_KEY_PATH);
                return sch;
            }
        };
        git = Git.cloneRepository()
                .setURI(url)
                .setDirectory(new File(path))
                .setTransportConfigCallback(
                        transport -> {
                            SshTransport sshTransport = (SshTransport) transport;
                            sshTransport.setSshSessionFactory(sshSessionFactory);
                        })
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