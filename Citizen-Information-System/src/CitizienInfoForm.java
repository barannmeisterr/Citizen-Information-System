import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Component;
public class CitizienInfoForm extends JFrame {
	/*
	 * Title: CitizenInfoForm Class
	 * Author: Arda Baran
	 * 
	 * Description:
	 * The CitizenInfoForm class is responsible for displaying all the information related to a citizen,
	 * including their personal details, family members, and phone numbers, adresses in a graphical user interface (GUI).
	 * 
	 * This class provides a user-friendly interface that allows users to view comprehensive details about a citizen,
	 * integrating data from multiple sources and presenting it in an organized manner. The GUI is designed to be 
	 * intuitive and accessible, enabling users to easily navigate through the information related to the citizen's 
	 * identity and family connections.
	 * 
	 * The CitizenInfoForm class also includes functionality to populate a table with the family members of a citizen. 
	 * The `DefaultTableModel` is used to manage the data in this table (`relativesTable`). When the table is populated, 
	 * it displays detailed information about each family member, such as their relationship to the citizen, ID, TCKN 
	 * (Turkish National Identification Number), name, surname, birth date, city, county, and parent details.
	 * 
	 * Additionally, the class implements an event listener (`MouseAdapter`) to handle user interactions with the table.
	 * Specifically, when a user clicks on the TCKN column of a selected row, the class retrieves the selected citizen's 
	 * details using the TCKN, fetches the citizen's family members and phone numbers from the database, and then displays 
	 * this information in a new `CitizenInfoForm` window.
	 * 
	 * The design ensures that users can easily drill down into specific citizen information directly from the family table, 
	 * providing a seamless and interactive experience.
	 */
	
	Citizien citizen;
	
	public CitizienInfoForm(Citizien citizen) {
		this.citizen=citizen;
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/resources/images/eye.png")));
		initComponents();
	}
	public Citizien getCitizien() {
		return citizen;
	}
	private void initComponents() {
		String id = Integer.toString(getCitizien().getId());
		String tc = getCitizien().getTc();
		String name = getCitizien().getName();
		String surname= getCitizien().getSurname();
		String birth=getCitizien().getBirthDate();
		String city=getCitizien().getCity();
		String county=getCitizien().getCounty();
		String motherName=getCitizien().getMotherName();
		String motherTc=getCitizien().getMotherTc();
		String fatherName= getCitizien().getFatherName();
		String fatherTc=getCitizien().getFatherTc();
		String nation = getCitizien().getNationality();
		List <Citizien> familia = getCitizien().getFamily();
		List<String> gsmNumbers = new ArrayList<>(getCitizien().getPhoneNumbers());
		javax.swing.JTextField birthJtf;
		     javax.swing.JTextField cityJtf;
		    javax.swing.JTextField countyJtf;
		     javax.swing.JTextField fatherNameJtf;
		     javax.swing.JTextField fatherTcJtf;
		     javax.swing.JList<String> gsmList;
		    javax.swing.JTextField idJtf;
		     javax.swing.JLabel jLabel1;
		javax.swing.JLabel jLabel10;
		   javax.swing.JLabel jLabel11;
		     javax.swing.JLabel jLabel12;
		    javax.swing.JLabel jLabel13;
		     javax.swing.JLabel jLabel14;
		     javax.swing.JLabel jLabel2;
		     javax.swing.JLabel jLabel3;
		     javax.swing.JLabel jLabel4;
		     javax.swing.JLabel jLabel5;
		    javax.swing.JLabel jLabel6;
		     javax.swing.JLabel jLabel7;
		     javax.swing.JLabel jLabel8;
		     javax.swing.JLabel jLabel9;
		
		     javax.swing.JPanel jPanel1;
		    
		   
		    javax.swing.JScrollPane jScrollPane1;
		     javax.swing.JScrollPane jScrollPane2;
		    javax.swing.JTextField motherNameJtf;
		   javax.swing.JTextField motherTcJtf;
		    javax.swing.JTextField nameJtf;
		    javax.swing.JTextField nationalityJtf;
		     javax.swing.JTable relativesTable;
		    javax.swing.JTextField surnameJtf;
		     javax.swing.JTextField tcJtf;
		    
	        jPanel1 = new javax.swing.JPanel();
	       jLabel1 = new javax.swing.JLabel();
	        jLabel2 = new javax.swing.JLabel();
	        jLabel3 = new javax.swing.JLabel();
	        jLabel4 = new javax.swing.JLabel();
	        jLabel5 = new javax.swing.JLabel();
	        jLabel6 = new javax.swing.JLabel();
	        jLabel7 = new javax.swing.JLabel();
	        jLabel8 = new javax.swing.JLabel();
	        jLabel9 = new javax.swing.JLabel();
	        jLabel10 = new javax.swing.JLabel();
	        jLabel11 = new javax.swing.JLabel();
	        jLabel12 = new javax.swing.JLabel();
	        nameJtf = new javax.swing.JTextField();
	        tcJtf = new javax.swing.JTextField();
	        idJtf = new javax.swing.JTextField();
	        surnameJtf = new javax.swing.JTextField();
	        countyJtf = new javax.swing.JTextField();
	        motherNameJtf = new javax.swing.JTextField();
	        motherTcJtf = new javax.swing.JTextField();
	        fatherNameJtf = new javax.swing.JTextField();
	        fatherTcJtf = new javax.swing.JTextField();
	        birthJtf = new javax.swing.JTextField();
	        cityJtf = new javax.swing.JTextField();
	        nationalityJtf = new javax.swing.JTextField();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        gsmList = new javax.swing.JList<>();
	        jLabel13 = new javax.swing.JLabel();
	        jScrollPane2 = new javax.swing.JScrollPane();
	       
	    
	      
	        relativesTable = new javax.swing.JTable();
	        jLabel14 = new javax.swing.JLabel();
            setTitle(name+" "+surname);
	        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

	        jLabel1.setText("id:");

	        jLabel2.setText("TC NO:");

	        jLabel3.setText("AD:");

	        jLabel4.setText("SOYAD:");

	        jLabel5.setText("NÜFUS İLÇE:");

	        jLabel6.setText("ANNE ADI:");

	        jLabel7.setText("ANNE TC NO:");

	        jLabel8.setText("BABA ADI:");

	        jLabel9.setText("DOĞUM TARİHİ:");

	        jLabel10.setText("NÜFUS İL:");

	        jLabel11.setText("BABA TC NO:");

	        jLabel12.setText("UYRUK:");

	        nameJtf.setEditable(false);

	        tcJtf.setEditable(false);
	    

	        idJtf.setEditable(false);
	      

	        surnameJtf.setEditable(false);

	        countyJtf.setEditable(false);

	        motherNameJtf.setEditable(false);

	        motherTcJtf.setEditable(false);

	        fatherNameJtf.setEditable(false);

	        fatherTcJtf.setEditable(false);

	        birthJtf.setEditable(false);

	        cityJtf.setEditable(false);

	        nationalityJtf.setEditable(false);

	        
	        jScrollPane1.setViewportView(gsmList);

	        jLabel13.setText("ÜZERİNE KAYITLI GSM NUMARALARI:");
             idJtf.setText(id);
             tcJtf.setText(tc);
             nameJtf.setText(name);
             surnameJtf.setText(surname);
             birthJtf.setText(birth);
             cityJtf.setText(city);
             countyJtf.setText(county);
             motherNameJtf.setText(motherName);
             motherTcJtf.setText(motherTc);
             fatherNameJtf.setText(fatherName);
             fatherTcJtf.setText(fatherTc);
             nationalityJtf.setText(nation);
             DefaultListModel listModel = new DefaultListModel();
             for (int i = 0; i < gsmNumbers.size(); i++)
             {
                 listModel.addElement(gsmNumbers.get(i));
             }
             
             gsmList.setModel(listModel);
             
             
             JScrollPane scrollPane = new JScrollPane();
 	        
             JLabel lblNewLabel = new JLabel("İKAMETGAH ADRESLERİ:");
 	        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
 	        jPanel1Layout.setHorizontalGroup(
 	        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
 	        		.addGroup(jPanel1Layout.createSequentialGroup()
 	        			.addContainerGap()
 	        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
 	        				.addGroup(jPanel1Layout.createSequentialGroup()
 	        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
 	        						.addGroup(jPanel1Layout.createSequentialGroup()
 	        							.addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
 	        							.addGap(18)
 	        							.addComponent(surnameJtf, 220, 220, 220))
 	        						.addGroup(jPanel1Layout.createSequentialGroup()
 	        							.addComponent(jLabel9, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
 	        							.addGap(18)
 	        							.addComponent(birthJtf, 220, 220, 220))
 	        						.addGroup(jPanel1Layout.createSequentialGroup()
 	        							.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
 	        							.addGap(18)
 	        							.addComponent(tcJtf, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
 	        						.addGroup(jPanel1Layout.createSequentialGroup()
 	        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
 	        								.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
 	        								.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
 	        							.addGap(18)
 	        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
 	        								.addComponent(idJtf, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
 	        								.addComponent(nameJtf, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)))
 	        						.addGroup(jPanel1Layout.createSequentialGroup()
 	        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
 	        								.addComponent(jLabel10, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
 	        								.addComponent(jLabel13, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))
 	        							.addPreferredGap(ComponentPlacement.RELATED)
 	        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
 	        								.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
 	        								.addComponent(cityJtf))))
 	        					.addGap(222)
 	        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
 	        						.addGroup(jPanel1Layout.createSequentialGroup()
 	        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
 	        								.addComponent(jLabel12, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
 	        								.addComponent(jLabel11, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
 	        								.addComponent(jLabel8, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
 	        								.addComponent(jLabel7, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
 	        								.addComponent(jLabel5, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
 	        								.addComponent(jLabel6, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
 	        							.addPreferredGap(ComponentPlacement.RELATED)
 	        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
 	        								.addComponent(nationalityJtf, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
 	        								.addComponent(motherNameJtf, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
 	        								.addComponent(countyJtf, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
 	        								.addComponent(motherTcJtf, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
 	        								.addComponent(fatherNameJtf, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
 	        								.addComponent(fatherTcJtf, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)))
 	        						.addGroup(jPanel1Layout.createSequentialGroup()
 	        							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
 	        							.addContainerGap())))
 	        				.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
 	        					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
 	        					.addGap(130))))
 	        );
 	        jPanel1Layout.setVerticalGroup(
 	        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
 	        		.addGroup(jPanel1Layout.createSequentialGroup()
 	        			.addGap(18)
 	        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
 	        				.addGroup(jPanel1Layout.createSequentialGroup()
 	        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
 	        						.addComponent(jLabel5)
 	        						.addComponent(countyJtf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
 	        					.addGap(18)
 	        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
 	        						.addComponent(jLabel2)
 	        						.addComponent(jLabel6)
 	        						.addComponent(motherNameJtf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
 	        						.addComponent(tcJtf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
 	        				.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
 	        					.addComponent(idJtf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
 	        					.addComponent(jLabel1)))
 	        			.addGap(18)
 	        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
 	        				.addComponent(jLabel3)
 	        				.addComponent(jLabel7)
 	        				.addComponent(motherTcJtf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
 	        				.addComponent(nameJtf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
 	        			.addGap(20)
 	        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
 	        				.addComponent(jLabel4)
 	        				.addComponent(jLabel8)
 	        				.addComponent(surnameJtf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
 	        				.addComponent(fatherNameJtf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
 	        			.addGap(18)
 	        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
 	        				.addComponent(jLabel9)
 	        				.addComponent(jLabel11)
 	        				.addComponent(birthJtf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
 	        				.addComponent(fatherTcJtf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
 	        			.addGap(18)
 	        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
 	        				.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
 	        					.addComponent(jLabel12)
 	        					.addComponent(nationalityJtf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
 	        				.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
 	        					.addComponent(jLabel10)
 	        					.addComponent(cityJtf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
 	        			.addGap(48)
 	        			.addComponent(lblNewLabel)
 	        			.addPreferredGap(ComponentPlacement.RELATED)
 	        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
 	        				.addGroup(jPanel1Layout.createSequentialGroup()
 	        					.addGap(30)
 	        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING, false)
 	        						.addComponent(jLabel13, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
 	        						.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)))
 	        				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
 	        			.addContainerGap())
 	        );
 	        jPanel1Layout.linkSize(SwingConstants.VERTICAL, new Component[] {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9, jLabel10, jLabel11, jLabel12, nameJtf, tcJtf, idJtf, surnameJtf, countyJtf, motherNameJtf, motherTcJtf, fatherNameJtf, fatherTcJtf, birthJtf, cityJtf, nationalityJtf});
 	        jPanel1Layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9, jLabel10, jLabel11, jLabel12, nameJtf, tcJtf, idJtf, surnameJtf, countyJtf, motherNameJtf, motherTcJtf, fatherNameJtf, fatherTcJtf, birthJtf, cityJtf, nationalityJtf});
 	        
 	        
 	        JTextArea adressJTA = new JTextArea();
 	        adressJTA.setLineWrap(true);
 	        adressJTA.setWrapStyleWord(true);
 	        for(String adres : getCitizien().getAddress()) {
 	        	adressJTA.append(adres);
 	            adressJTA.append("\n");
 	        }
 	        scrollPane.setViewportView(adressJTA);
 	        jPanel1.setLayout(jPanel1Layout);

	        relativesTable.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {

	            },
	            new String [] {
	                "YAKINLIK", "id", "TC NO", "AD", "SOYAD", "DOĞUM TARİHİ", "NÜFUS İL", "NÜFUS İLÇE", "ANNE ADI", "ANNE TC", "BABA ADI", "BABA TC", "UYRUK"
	            }
	        ));
	        
	        DefaultTableModel model = (DefaultTableModel) relativesTable.getModel();
	        model.setRowCount(0); 

	        for (Citizien relative : familia) {
	            model.addRow(new Object[]{
	                relative.getRelation(), 
	                relative.getId(),           
	                relative.getTc(),           
	                relative.getName(),         
	                relative.getSurname(),      
	                relative.getBirthDate(),    
	                relative.getCity(),         
	                relative.getCounty(),       
	                relative.getMotherName(),   
	                relative.getMotherTc(),    
	                relative.getFatherName(),   
	                relative.getFatherTc(),     
	                relative.getNationality()   
	            });
	        }
	        relativesTable.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                int row = relativesTable.getSelectedRow();
	                int column = relativesTable.getSelectedColumn();

	                
	                if (column == 2 && row != -1) {
	                    String tc = relativesTable.getValueAt(row, column).toString();
	                    try {
	                        Citizien selectedCitizen = Citizien.getCitizenByTc(tc);
	                        selectedCitizen.findFamilyMembers();
	                        selectedCitizen.findPhoneNumbers();
	                        selectedCitizen.findAdresses();
	                        if (selectedCitizen != null) {
	                            CitizienInfoForm citizenInfoForm = new CitizienInfoForm(selectedCitizen);
	                            citizenInfoForm.setVisible(true);
	                        }
	                    } catch (SQLException ex) {
	                        ex.printStackTrace(); 
	                        
	                    }
	                }
	            }
	        });

	
	        
	        jScrollPane2.setViewportView(relativesTable);

	        jLabel14.setText("Aile Bireyleri:");

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        layout.setHorizontalGroup(
	        	layout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addContainerGap()
	        			.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
	        				.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        				.addComponent(jLabel14, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
	        				.addComponent(jScrollPane2))
	        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	        	layout.createParallelGroup(Alignment.LEADING)
	        		.addGroup(layout.createSequentialGroup()
	        			.addContainerGap()
	        			.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	        			.addPreferredGap(ComponentPlacement.UNRELATED)
	        			.addComponent(jLabel14)
	        			.addGap(2)
	        			.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
	        			.addContainerGap(30, Short.MAX_VALUE))
	        );
	        getContentPane().setLayout(layout);

	        pack();
	    }// </editor-fold>                        	
	 

}
