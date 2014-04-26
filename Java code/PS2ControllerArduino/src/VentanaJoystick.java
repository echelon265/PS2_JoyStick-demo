import java.awt.*;
import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * 
 * @author Sergio Anguita
 *
 */
public class VentanaJoystick {

	private JFrame frmControladorPsjoystick;
	private SerialReader leer;
	private static JButton btnConectar;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					VentanaJoystick window = new VentanaJoystick();
					window.frmControladorPsjoystick.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaJoystick() {
		initialize();
	}

	private void initialize() {
		frmControladorPsjoystick = new JFrame();
		frmControladorPsjoystick.getContentPane().setBackground(Color.GRAY);
		frmControladorPsjoystick.setUndecorated(true);
		frmControladorPsjoystick.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaJoystick.class.getResource("/img/PS2Joystick logo.png")));
		frmControladorPsjoystick.setTitle("Controlador PS2Joystick");
		frmControladorPsjoystick.setBounds(100, 100, 387, 412);
		frmControladorPsjoystick.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmControladorPsjoystick.setLocationRelativeTo(null);
		
		final JLabel lblcontrol = new JLabel("");
		lblcontrol.setBounds(134, 113, 110, 110);
		lblcontrol.setIcon(new ImageIcon(VentanaJoystick.class.getResource("/img/control.png")));
		frmControladorPsjoystick.getContentPane().setLayout(null);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setBackground(Color.BLACK);
		btnCerrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		
		btnConectar = new JButton("Conectar");
		btnConectar.setBackground(Color.BLACK);
		btnConectar.setBounds(288, 344, 89, 23);
		frmControladorPsjoystick.getContentPane().add(btnConectar);
		btnCerrar.setBounds(288, 378, 89, 23);
		frmControladorPsjoystick.getContentPane().add(btnCerrar);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(VentanaJoystick.class.getResource("/img/PS2Joystick logo.png")));
		lblIcon.setBounds(379, 69, 8, 256);
		frmControladorPsjoystick.getContentPane().add(lblIcon);
		frmControladorPsjoystick.getContentPane().add(lblcontrol);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(64, 40, 256, 256);
		lblFondo.setIcon(new ImageIcon(VentanaJoystick.class.getResource("/img/fondo.png")));
		frmControladorPsjoystick.getContentPane().add(lblFondo);
		
		final JLabel lblPsRemovedJoystick = new JLabel("PS2 Removed Joystick: Current Status");
		lblPsRemovedJoystick.setForeground(Color.WHITE);
		lblPsRemovedJoystick.setHorizontalAlignment(SwingConstants.CENTER);
		lblPsRemovedJoystick.setBounds(51, 15, 269, 14);
		frmControladorPsjoystick.getContentPane().add(lblPsRemovedJoystick);
		
		final JLabel lblEstadoDesconectado = new JLabel("ESTADO ARDUINO: DESCONECTADO");
		lblEstadoDesconectado.setForeground(Color.WHITE);
		lblEstadoDesconectado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEstadoDesconectado.setBounds(10, 387, 269, 14);
		frmControladorPsjoystick.getContentPane().add(lblEstadoDesconectado);
		
		JLabel lblFondoFrame = new JLabel("");
		lblFondoFrame.setIcon(null);
		lblFondoFrame.setBounds(0, 0, 387, 415);
		frmControladorPsjoystick.getContentPane().add(lblFondoFrame);
		
		//LISTENERS
		
		btnConectar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(btnConectar.getText().equals("Conectar")){			
					//Solo valido para windows
					String ruta = System.getenv("ProgramFiles")+"\\Java";
					File[] f = new File(ruta).listFiles();
					for (int i = 0; i < f.length; i++) {
						if(f[i].getName().toString().toLowerCase().startsWith("jre")){
							ruta = ruta+"\\"+f[i].getName().toString()+"\\bin\\rxtxSerial.dll";
							break;
						}
					}
					File temp = new File(ruta);
					if(temp.exists()){
						lblEstadoDesconectado.setText("ESTADO ARDUINO: CONECTANDO");
						leer = new SerialReader(lblcontrol, lblPsRemovedJoystick, lblEstadoDesconectado);
						leer.start();
					}
					else{
						JOptionPane.showMessageDialog(null,"No se han encontrado las libreiras RXTX en este sistema","Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					btnConectar.setText("Conectar");
					leer.close();
				}
			}
		});
	}
	public static JButton getBoton(){
		return btnConectar;
	}
}
