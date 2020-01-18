import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class NetBanking extends JFrame implements ActionListener
{
	JLabel l,l1,l2,l3;
	JButton b,b1,b5,b6,b7,b8,b2,b3,b4,b9,bb;
	JTextField t1;
	JPasswordField p1;  
	int a=2500000,c=0,z;
	
	NetBanking(String s)
	{
		super(s);
		l=new JLabel(" MK BANK OF INDIA");
		l.setBounds(100,80,450,50);
		add(l);
		
		l=new JLabel("WELCOME TO NET BANKING SERVICE");
		l.setBounds(70,50,450,20);
		add(l);
		
		l1=new JLabel("USER ID :");
		l2=new JLabel("PASSWORD  :");
		
		b=new JButton("LOGIN");

		JCheckBox c1 = new JCheckBox("  I ACCEPT ALL TERMS AND CONDITIONS", true);  
        c1.setBounds(50,250, 500,50);  
        add(c1);
		
		t1=new JTextField();  
		t1.setBounds(150,150, 200,30);  
		p1=new JPasswordField();  
		p1.setBounds(150,200, 200,30);  
		  
		t1.addActionListener(this);
		p1.addActionListener(this);
		
		b.addActionListener(this);
		
		l1.setBounds(50,150,100,30);
		l2.setBounds(50,200,100,30);
		
		b.setBounds(100,300,100,30);
		add(l1);
		add(l2);
		add(b);
		add(t1);
		add(p1);
		
		b5=new JButton("USER PROFILE");  
		b6=new JButton("CHECK BALANCE");
		b7=new JButton("TRANSFER CASH");
		b8=new JButton("COMPLAINTS");
			
			b5.setBounds(0,600,200, 40); 
			b6.setBounds(250,600,200, 40); 
			b7.setBounds(500,600,200, 40); 
			b8.setBounds(750,600,200, 40); 
			
			b5.addActionListener(this);
			b6.addActionListener(this);
			b7.addActionListener(this);
			b8.addActionListener(this);
			
			add(b5);
			add(b6);
			add(b7);
			add(b8);
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setLocation(250,100);
		setVisible(true);
		setSize(1000,800);
	}
	public void actionPerformed(ActionEvent e)
	{
		String s1=t1.getText();   
		String s2=new String(p1.getPassword());
		//////////////////////////login//////////////////////////
		if(e.getSource()==b)
		{
			String msg;
            msg = "Login Successful";
            JOptionPane.showMessageDialog(null, msg);
		}
			/////////////////////////USER DETAILS/////////////////////////////
		else if(e.getSource()==b5)
		{
			
			JFrame f=new JFrame();  			
			
			JTextField t6,t7,t8,t9,t10,t11,t12,t13;			
 
			JLabel l4=new JLabel("MK BANK OF INDIA");  
			l4.setBounds(400,40,200, 40); 
			f.add(l4);
			
			JLabel l5=new JLabel("USER PROFILE");  
			l5.setBounds(300,70,200, 40); 
			f.add(l5); 

			
			JLabel l6=new JLabel("USER NAME : ");  
			l6.setBounds(100,150,200, 40); 
			f.add(l6);
			  
			t6=new JTextField();
			t6.setText("MEIYAZHAGAN KULANDAIVEL");  
			t6.setBounds(300,150, 200,30);
			t6.setEditable(false);			
			f.add(t6); 	
			
			JLabel l7=new JLabel("ACCOUNT NO : ");  
			l7.setBounds(100,200,200, 40); 
			f.add(l7);
			
			t7=new JTextField("6078 5184 1274 3157");  
			t7.setBounds(300,200, 200,30);  
			t7.setEditable(false);
			f.add(t7);
			
			JLabel l8=new JLabel("ADDRESS : ");  
			l8.setBounds(100,250,200, 40); 
			f.add(l8);
			
			t8=new JTextField("NAMAKKAL");  
			t8.setBounds(300,250, 200,30);
			t8.setEditable(false);			
			f.add(t8);
			
			JLabel l9=new JLabel("PHONE NO : ");  
			l9.setBounds(100,300,200, 40); 
			f.add(l9);
			
			t9=new JTextField("90803 35279");  
			t9.setBounds(300,300, 200,30); 
			t9.setEditable(false);			
			f.add(t9);
			
			JLabel l10=new JLabel("OPERATIONAL MODE : ");  
			l10.setBounds(100,350,200, 40); 
			f.add(l10);
			
			t10=new JTextField("SELF");  
			t10.setBounds(300,350, 200,30); 
			t10.setEditable(false);			
			f.add(t10);
			
			JLabel l11=new JLabel("DATE OF OPENING : ");  
			l11.setBounds(100,400,200, 40); 
			f.add(l11);
			t11=new JTextField(" 11 FEB 2011");  
			t11.setBounds(300,400, 200,30);
			t11.setEditable(false);
			f.add(t11);
			
			JLabel l12=new JLabel("ATM NUMBER : ");  
			l12.setBounds(100,450,200, 40); 
			f.add(l12);
			
			t12=new JTextField("**** **** **** *852");  
			t12.setBounds(300,450, 200,30); 
			t12.setEditable(false);
			f.add(t12);
			
			JLabel l13=new JLabel("CARD VALID UPTO : ");  
			l13.setBounds(100,500,200, 40); 
			f.add(l13);
			
			t13=new JTextField("24 - AUG - 2020");  
			t13.setBounds(300,500, 200,30); 
			t13.setEditable(false);
			f.add(t13);
			
			b1=new JButton("Ok");
			b1.setBounds(250,600,100,30);
			f.add(b1);
			b1.addActionListener(this);
			
			f.setSize(1000,800);  
			f.setLayout(null);
			f.setVisible(true);
			
		}  
		////////////////////////////////CHECK BALANCE////////////////////////////////////
		else if(e.getSource()==b6)
		{
			JFrame f=new JFrame("MK BANK OF INDIA");
			
			JLabel l=new JLabel(" MK BANK OF INDIA");
			l.setBounds(70,50,450,20);
			f.add(l);
		
			JLabel l4=new JLabel("VERIFY BALANCE");
			l4.setBounds(100,80,450,50);
			f.add(l4);
			
			JLabel l18=new JLabel("CURRENT BALANCE : ");  
			l18.setBounds(100,250,200, 40); 
			f.add(l18);
			
			JTextField t18=new JTextField("Rs ."+ a);  
			t18.setBounds(300,250, 200,30);
			t18.setEditable(false);			
			f.add(t18);
			
			b1=new JButton("Ok");
			b1.setBounds(250,400,100,30);
			f.add(b1);
			b1.addActionListener(this);
			
			f.add(l);
			f.setSize(800,600);  
			f.setLayout(null);
			f.setVisible(true);
		} 
		////////////////////////////////TRANSFER CASH////////////////////////////////
		else if(e.getSource()==b7)
		{
			JFrame f=new JFrame("MK BANK OF INDIA");
			
			JLabel l=new JLabel(" MK BANK OF INDIA");
			l.setBounds(70,50,450,20);
			f.add(l);
		
			JLabel l4=new JLabel("BALANCE TRANSFER");
			l4.setBounds(100,80,450,50);
			f.add(l4);
			
			JLabel l18=new JLabel("CURRENT BALANCE : ");  
			l18.setBounds(100,150,200, 40); 
			f.add(l18);
			
			JTextField t18=new JTextField("Rs. "+a);  
			t18.setBounds(300,150, 200,30);
			t18.setEditable(false);			
			f.add(t18);
			//t18.addActionListener(this);
			
			JLabel l22=new JLabel("PARTNER'S ACC NO: ");  
			l22.setBounds(100,200,200, 40); 
			f.add(l22);
			
			JTextField t55=new JTextField();  
			t55.setBounds(300,200, 200,30);
			//t18.setEditable(false);			
			f.add(t55);
			
			
			JLabel l24=new JLabel("AMOUNT TO TRANSFER : ");  
			l24.setBounds(100,250,200, 40); 
			f.add(l24);
			
			JTextField t77=new JTextField();  
			t77.setBounds(300,250, 200,30);
			t77.addActionListener(this);			
			f.add(t77);
			
			JLabel l23=new JLabel("UPI PIN : ");  
			l23.setBounds(100,300,200, 40); 
			f.add(l23);
			
			JTextField t88=new JTextField();  
			t88.setBounds(300,300, 200,30);
			//t18.setEditable(false);			
			f.add(t88);
			
			bb=new JButton("Submit");
			bb.setBounds(150,400,100,30);
			f.add(bb);
			//bb.addActionListener(this);
			
			b1=new JButton("Proceed");
			b1.setBounds(250,400,100,30);
			f.add(b1);
			b1.addActionListener(this);
			
			f.setSize(800,600);  
			f.setLayout(null);  
			f.setVisible(true);
		}
		//////////////////////////////COMPLAINT////////////////////////////////
		else if(e.getSource()==b8)
		{
			JFrame f=new JFrame("MK BANK OF INDIA");
			
			JLabel l1=new JLabel(" MK BANK OF INDIA");
			l1.setBounds(70,50,450,20);
			f.add(l1);
		
			JLabel l2=new JLabel("DESCRIBE YOUR COMPLAINT HERE..");
			l2.setBounds(100,80,450,50);
			f.add(l2);
			  
			JTextArea area=new JTextArea(" ");  
			area.setBounds(70,120,500,300); 
			
			b1=new JButton("Submit");
			b1.setBounds(250,450,100,30);
			f.add(b1);
			b1.addActionListener(this);
			
			JLabel l22=new JLabel("CUSTOMER CARE : 95249 14630");
			l22.setBounds(600,500,450,50);
			f.add(l22);
			
			JLabel l23=new JLabel("FOLLOW US @ 	mkbanks@hari.ac.in");
			l23.setBounds(600,550,450,50);
			f.add(l23);
			
			JLabel l24=new JLabel("CONTACT US AT mknetbanking@gmail.com");
			l24.setBounds(600,600,450,50);
			f.add(l24);
			
			f.add(area);  
			f.setSize(1000,700);  
			f.setLayout(null);  
			f.setVisible(true);
		}
		if(e.getSource()==b1)
			{	
				JOptionPane.showMessageDialog(null, "OPERATION COMPLETED");
			}
			
		/*if(e.getSource()==bb)
			{
			String s=t77.getText();    
			int x=Integer.parseInt(s);
			c+=(a-x);
				c+=a;
				String result=String.valueOf(c);  
				t18.setText(result);  
			
			}*/
		
	}
	
	public static void main(String s[])
	{
		new NetBanking("MK BANK OF INDIA");
	}

}


