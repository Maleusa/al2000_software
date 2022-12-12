package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class DaoConnection{
	
	private static DaoConnection instance;
	static Session session;
	String sshUser;	// strappah
	String sshPassword;	//Pa$$word
	String sshHost = "im2ag-mandelbrot.univ-grenoble-alpes.fr";
	int sshPort =22;
	int nLocalPort=38912;
	int nRemotePort = 1521;
	Connection base;

	private DaoConnection() {
		try (Scanner scan = new Scanner(System.in)) {
			System.out.println("user :");
			sshUser=scan.nextLine();
			System.out.println("password :");
			sshPassword=scan.nextLine();
		}
	}
	
	public static DaoConnection getInstance() {
		if (DaoConnection.instance == null) {
			DaoConnection.instance = new DaoConnection();
		}
		return DaoConnection.instance;
	}
	
	 /**
     * Initialize ssh tunnel between two server sshHost and sshRemoteHost
     * 
     * @param sshUser
     * @param sshPassword
     * @param sshHost
     * @param sshPort
     * @param strRemoteHost
     * @param nLocalPort
     * @param nRemotePort
     * @return
     * @throws JSchException
     */
	private static Session doSshTunnel(String sshUser, String sshPassword, String sshHost, int sshPort,
			String strRemoteHost, int nLocalPort, int nRemotePort) throws JSchException {
		final JSch jsch = new JSch();
		session = jsch.getSession(sshUser, sshHost, 22);
		session.setPassword(sshPassword);
		try {
			final Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			System.out.println("connected to mandelbrot");
			session.setPortForwardingL(nLocalPort, strRemoteHost, nRemotePort);
			System.out.println("tunnel connected");
		} catch (JSchException e) {
			System.out.println("error tunnel");
			e.printStackTrace(); 
			session.disconnect();
		}
		return session;
	}
	
	 /**
     * Initialize the sqlplus connection to a Oracle server
     * @return
     */
	public Connection connectDB() {
		try {
			String sshHost = "im2ag-mandelbrot.univ-grenoble-alpes.fr";

			String strRemoteHost="im2ag-oracle.univ-grenoble-alpes.fr";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			session =doSshTunnel(sshUser, sshPassword, sshHost, sshPort, strRemoteHost, nLocalPort, nRemotePort);
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			base = DriverManager.getConnection("jdbc:oracle:thin:@localhost:"+nLocalPort+":im2ag", sshUser, "942e7b79fc"); // connexion 942e7b79fc  94bf0ae10f
			base.setAutoCommit(false);
			System.out.println("base connected");

			return base;
		} catch (Exception err) { 
			System.out.println("error base connection");
			err.printStackTrace();
			session.disconnect();
			return null;
		}
	}
	public Connection getBase() {
		return this.base;
	}
	
	/**
     * exit sqlplus connection
     */
	public void disconnectDB(){
		try {
			base.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.disconnect();// fermeture de la connexion
	}

}
