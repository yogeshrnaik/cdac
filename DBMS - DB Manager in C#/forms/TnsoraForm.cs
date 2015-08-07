/********************************************************************************/
using System;
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.IO;
/********************************************************************************/
namespace DBManager.forms
{
	/// <summary>
	/// Summary description for tnsora.
	/// </summary>
/********************************************************************************/
	public class TnsoraForm : System.Windows.Forms.Form
	{
/********************************************************************************/
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.TabPage tabPage1;
		private System.Windows.Forms.Label lblSID;
		private System.Windows.Forms.Label lblPort;
		private System.Windows.Forms.Label lblProtocol;
		private System.Windows.Forms.Label lblHost;
		private System.Windows.Forms.Label lblDb;
		private System.Windows.Forms.TabPage tabPage2;
		private System.Windows.Forms.TabControl tnsTab;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.Label label3;
		private System.Windows.Forms.Label label4;
		private System.Windows.Forms.Label label5;
		private System.Windows.Forms.Label label6;
		private System.Windows.Forms.Label label7;
		private System.Windows.Forms.TabPage tbpgText;
		private System.Windows.Forms.TabPage tbpgEntry;
		private System.Windows.Forms.Button btnSave;
		private System.Windows.Forms.Button btnCancel;
		private System.Windows.Forms.TextBox txttns;
		private System.Windows.Forms.TextBox txtSID;
		private System.Windows.Forms.TextBox txtPort;
		private System.Windows.Forms.TextBox txtProtocol;
		private System.Windows.Forms.TextBox txtHost;
		private System.Windows.Forms.TextBox txtDB;
		private static bool l_changed;
/********************************************************************************/
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;
		private System.Windows.Forms.Button btnClear;
/********************************************************************************/
		private string original_tnsora_contents;
/********************************************************************************/
		public TnsoraForm()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();
			this.ShowInTaskbar = false;
			this.tnsTab.SelectedIndexChanged += new EventHandler(tnsTab_SelectedIndexChanged);
			this.tbpgEntry.LostFocus += new EventHandler(tbpgEntry_LostFocus);
			
			original_tnsora_contents = getTnsoraFileContents();
			this.txttns.Text = original_tnsora_contents;

			this.tnsTab.KeyUp += new KeyEventHandler(tnsTab_KeyUp);
			
			
			this.btnSave.Enabled = false;
		}
/********************************************************************************/
		private string getTnsoraFileContents()
		{
			try
			{
				FileStream file;
				file = new FileStream("tnsnames.ora", FileMode.OpenOrCreate, FileAccess.ReadWrite);

				// Create a new stream to read from a file
				StreamReader sr = new StreamReader(file);

				// Read contents of file into a string
				string s = sr.ReadToEnd();
				this.txttns.Text = s;
				// Close StreamReader
				sr.Close();
				// Close file
				file.Close();
				return s;
			}
			catch
			{
				return "";
			}
		}
/********************************************************************************/
		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		protected override void Dispose( bool disposing )
		{
			if( disposing )
			{
				if(components != null)
				{
					components.Dispose();
				}
			}
			base.Dispose( disposing );
		}
/********************************************************************************/
		#region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.label1 = new System.Windows.Forms.Label();
			this.tabPage1 = new System.Windows.Forms.TabPage();
			this.tabPage2 = new System.Windows.Forms.TabPage();
			this.tnsTab = new System.Windows.Forms.TabControl();
			this.tbpgText = new System.Windows.Forms.TabPage();
			this.label2 = new System.Windows.Forms.Label();
			this.txttns = new System.Windows.Forms.TextBox();
			this.tbpgEntry = new System.Windows.Forms.TabPage();
			this.txtSID = new System.Windows.Forms.TextBox();
			this.txtPort = new System.Windows.Forms.TextBox();
			this.txtProtocol = new System.Windows.Forms.TextBox();
			this.txtHost = new System.Windows.Forms.TextBox();
			this.txtDB = new System.Windows.Forms.TextBox();
			this.label3 = new System.Windows.Forms.Label();
			this.label4 = new System.Windows.Forms.Label();
			this.label5 = new System.Windows.Forms.Label();
			this.label6 = new System.Windows.Forms.Label();
			this.label7 = new System.Windows.Forms.Label();
			this.btnSave = new System.Windows.Forms.Button();
			this.btnCancel = new System.Windows.Forms.Button();
			this.btnClear = new System.Windows.Forms.Button();
			this.tnsTab.SuspendLayout();
			this.tbpgText.SuspendLayout();
			this.tbpgEntry.SuspendLayout();
			this.SuspendLayout();
			// 
			// label1
			// 
			this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.label1.Location = new System.Drawing.Point(24, 8);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(200, 16);
			this.label1.TabIndex = 1;
			// 
			// tabPage1
			// 
			this.tabPage1.Location = new System.Drawing.Point(0, 0);
			this.tabPage1.Name = "tabPage1";
			this.tabPage1.TabIndex = 0;
			// 
			// tabPage2
			// 
			this.tabPage2.Location = new System.Drawing.Point(0, 0);
			this.tabPage2.Name = "tabPage2";
			this.tabPage2.TabIndex = 0;
			// 
			// tnsTab
			// 
			this.tnsTab.Controls.Add(this.tbpgText);
			this.tnsTab.Controls.Add(this.tbpgEntry);
			this.tnsTab.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.tnsTab.Location = new System.Drawing.Point(16, 32);
			this.tnsTab.Name = "tnsTab";
			this.tnsTab.SelectedIndex = 0;
			this.tnsTab.Size = new System.Drawing.Size(368, 224);
			this.tnsTab.TabIndex = 1;
			this.tnsTab.SelectedIndexChanged += new System.EventHandler(this.tnsTab_SelectedIndexChanged);
			// 
			// tbpgText
			// 
			this.tbpgText.Controls.Add(this.label2);
			this.tbpgText.Controls.Add(this.txttns);
			this.tbpgText.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.tbpgText.Location = new System.Drawing.Point(4, 22);
			this.tbpgText.Name = "tbpgText";
			this.tbpgText.Size = new System.Drawing.Size(360, 198);
			this.tbpgText.TabIndex = 0;
			this.tbpgText.Text = "tnsnames.ora";
			// 
			// label2
			// 
			this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.label2.Location = new System.Drawing.Point(24, 8);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(200, 16);
			this.label2.TabIndex = 1;
			this.label2.Text = "Contents of tnsnames.ora";
			// 
			// txttns
			// 
			this.txttns.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.txttns.Location = new System.Drawing.Point(24, 24);
			this.txttns.Multiline = true;
			this.txttns.Name = "txttns";
			this.txttns.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
			this.txttns.Size = new System.Drawing.Size(296, 168);
			this.txttns.TabIndex = 0;
			this.txttns.Text = "";
			this.txttns.TextChanged += new System.EventHandler(this.txttns_TextChanged);
			// 
			// tbpgEntry
			// 
			this.tbpgEntry.Controls.Add(this.txtSID);
			this.tbpgEntry.Controls.Add(this.txtPort);
			this.tbpgEntry.Controls.Add(this.txtProtocol);
			this.tbpgEntry.Controls.Add(this.txtHost);
			this.tbpgEntry.Controls.Add(this.txtDB);
			this.tbpgEntry.Controls.Add(this.label3);
			this.tbpgEntry.Controls.Add(this.label4);
			this.tbpgEntry.Controls.Add(this.label5);
			this.tbpgEntry.Controls.Add(this.label6);
			this.tbpgEntry.Controls.Add(this.label7);
			this.tbpgEntry.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.tbpgEntry.Location = new System.Drawing.Point(4, 22);
			this.tbpgEntry.Name = "tbpgEntry";
			this.tbpgEntry.Size = new System.Drawing.Size(360, 198);
			this.tbpgEntry.TabIndex = 1;
			this.tbpgEntry.Text = "New Entry";
			this.tbpgEntry.Visible = false;
			// 
			// txtSID
			// 
			this.txtSID.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.txtSID.Location = new System.Drawing.Point(112, 152);
			this.txtSID.Name = "txtSID";
			this.txtSID.Size = new System.Drawing.Size(168, 20);
			this.txtSID.TabIndex = 9;
			this.txtSID.Text = "";
			this.txtSID.TextChanged += new System.EventHandler(this.txtSID_TextChanged);
			// 
			// txtPort
			// 
			this.txtPort.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.txtPort.Location = new System.Drawing.Point(112, 118);
			this.txtPort.Name = "txtPort";
			this.txtPort.Size = new System.Drawing.Size(168, 20);
			this.txtPort.TabIndex = 8;
			this.txtPort.Text = "";
			this.txtPort.TextChanged += new System.EventHandler(this.txtPort_TextChanged);
			// 
			// txtProtocol
			// 
			this.txtProtocol.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.txtProtocol.Location = new System.Drawing.Point(112, 84);
			this.txtProtocol.Name = "txtProtocol";
			this.txtProtocol.Size = new System.Drawing.Size(168, 20);
			this.txtProtocol.TabIndex = 7;
			this.txtProtocol.Text = "";
			this.txtProtocol.TextChanged += new System.EventHandler(this.txtProtocol_TextChanged);
			// 
			// txtHost
			// 
			this.txtHost.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.txtHost.Location = new System.Drawing.Point(112, 50);
			this.txtHost.Name = "txtHost";
			this.txtHost.Size = new System.Drawing.Size(168, 20);
			this.txtHost.TabIndex = 6;
			this.txtHost.Text = "";
			this.txtHost.TextChanged += new System.EventHandler(this.txtHost_TextChanged);
			// 
			// txtDB
			// 
			this.txtDB.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.txtDB.Location = new System.Drawing.Point(112, 16);
			this.txtDB.Name = "txtDB";
			this.txtDB.Size = new System.Drawing.Size(168, 20);
			this.txtDB.TabIndex = 5;
			this.txtDB.Text = "";
			this.txtDB.TextChanged += new System.EventHandler(this.txtDB_TextChanged);
			// 
			// label3
			// 
			this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.label3.Location = new System.Drawing.Point(24, 152);
			this.label3.Name = "label3";
			this.label3.Size = new System.Drawing.Size(88, 20);
			this.label3.TabIndex = 4;
			this.label3.Text = "SID :";
			// 
			// label4
			// 
			this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.label4.Location = new System.Drawing.Point(24, 118);
			this.label4.Name = "label4";
			this.label4.Size = new System.Drawing.Size(88, 24);
			this.label4.TabIndex = 3;
			this.label4.Text = "Port No. :";
			// 
			// label5
			// 
			this.label5.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.label5.Location = new System.Drawing.Point(24, 84);
			this.label5.Name = "label5";
			this.label5.Size = new System.Drawing.Size(88, 24);
			this.label5.TabIndex = 2;
			this.label5.Text = "Protocol :";
			// 
			// label6
			// 
			this.label6.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.label6.Location = new System.Drawing.Point(24, 50);
			this.label6.Name = "label6";
			this.label6.Size = new System.Drawing.Size(88, 24);
			this.label6.TabIndex = 1;
			this.label6.Text = "Host :";
			// 
			// label7
			// 
			this.label7.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((System.Byte)(0)));
			this.label7.Location = new System.Drawing.Point(24, 16);
			this.label7.Name = "label7";
			this.label7.Size = new System.Drawing.Size(88, 24);
			this.label7.TabIndex = 0;
			this.label7.Text = "Database :";
			// 
			// btnSave
			// 
			this.btnSave.Location = new System.Drawing.Point(216, 272);
			this.btnSave.Name = "btnSave";
			this.btnSave.TabIndex = 2;
			this.btnSave.Text = "Save";
			this.btnSave.Click += new System.EventHandler(this.btnSave_Click);
			// 
			// btnCancel
			// 
			this.btnCancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
			this.btnCancel.Location = new System.Drawing.Point(312, 272);
			this.btnCancel.Name = "btnCancel";
			this.btnCancel.TabIndex = 3;
			this.btnCancel.Text = "Cancel";
			this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
			// 
			// btnClear
			// 
			this.btnClear.Location = new System.Drawing.Point(120, 272);
			this.btnClear.Name = "btnClear";
			this.btnClear.TabIndex = 4;
			this.btnClear.Text = "Clear";
			this.btnClear.Click += new System.EventHandler(this.btnClear_Click);
			// 
			// TnsoraForm
			// 
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.ClientSize = new System.Drawing.Size(410, 311);
			this.Controls.Add(this.btnClear);
			this.Controls.Add(this.btnCancel);
			this.Controls.Add(this.btnSave);
			this.Controls.Add(this.tnsTab);
			this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
			this.MaximizeBox = false;
			this.MinimizeBox = false;
			this.Name = "TnsoraForm";
			this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
			this.Text = "Tnsnames.ora";
			this.tnsTab.ResumeLayout(false);
			this.tbpgText.ResumeLayout(false);
			this.tbpgEntry.ResumeLayout(false);
			this.ResumeLayout(false);

		}
		#endregion
/********************************************************************************/
		private void btnSave_Click(object sender, System.EventArgs e)
		{
			if (this.tnsTab.SelectedTab.Text.Equals("tnsnames.ora"))
			{
				tbpgText_Save();
				this.btnSave.Enabled = false;
			}
			else if (this.tnsTab.SelectedTab.Text.Equals("New Entry"))
			{
				tbpgEntry_Save();
				this.btnSave.Enabled = false;
			}
		}
/********************************************************************************/
		private void btnCancel_Click(object sender, System.EventArgs e)
		{
			if (this.btnSave.Enabled.Equals(true))
			{
				DialogResult dr = MessageBox.Show("Do you want to Save the Changes?","DB Manager",MessageBoxButtons.YesNo,MessageBoxIcon.Question);
				if (dr == DialogResult.Yes) 
				{
					if (this.tnsTab.SelectedTab.Text.Equals("tnsnames.ora"))
					{
						tbpgText_Save();
					}
					else if (this.tnsTab.SelectedTab.Text.Equals("New Entry"))
					{
						int entry_save = tbpgEntry_Save();
						if (entry_save == 1)
							tbpgEntry_Refresh();
						else 
							return;
					}
				}
				else if (dr == DialogResult.No) 
				{
					tbpgEntry_Refresh();
					this.Close();
				}
				
			}
			else
			{
				this.Close();
			}
		}
/********************************************************************************/
		private void tbpgEntry_LostFocus(object sender, System.EventArgs e)
		{
			MessageBox.Show("lost");
		}
/********************************************************************************/
		private bool runTabChange = true;
/********************************************************************************/
		private void tnsTab_SelectedIndexChanged(object sender, System.EventArgs e)
		{
			if (runTabChange)
			{
				if (this.btnSave.Enabled)
				{
					runTabChange = false;
					this.tnsTab.SelectedIndex = (this.tnsTab.SelectedIndex == 0) ? 1 : 0;
					runTabChange = true;
					DialogResult dr = MessageBox.Show("Do you want to Save the Changes?", "DB Manager",MessageBoxButtons.YesNo,MessageBoxIcon.Question);
					if (dr == DialogResult.Yes) 
					{
						if (this.tnsTab.SelectedTab.Text.Equals("tnsnames.ora"))
						{
							original_tnsora_contents = this.txttns.Text;
							if (tbpgText_Save() == 1)
							{
								runTabChange = false;
								this.tnsTab.SelectedIndex = 1;
								runTabChange = true;
							}
						}
						else if (this.tnsTab.SelectedTab.Text.Equals("New Entry"))
						{
							int entry_save = tbpgEntry_Save();
							if (entry_save == 1)
							{
								refreshNewEntryTab = true;
								tbpgEntry_Refresh();
							}
							else
							{
								refreshNewEntryTab = false;
								runTabChange = false;
								this.tnsTab.SelectedIndex = 1;
								runTabChange = true;
							}
							original_tnsora_contents = getTnsoraFileContents();
							this.txttns.Text = original_tnsora_contents;
						}
					}
					else
					{
						this.txttns.Text = original_tnsora_contents;
						runTabChange = false;
						this.tnsTab.SelectedIndex = (this.tnsTab.SelectedIndex == 0) ? 1 : 0;
						runTabChange = true;
					}
				}
				else if (refreshNewEntryTab)
				{
					this.tbpgEntry_Refresh();
				}
				this.btnSave.Enabled = false;
			}
			}
/********************************************************************************/
		private bool refreshNewEntryTab = true;
/********************************************************************************/
		private void tbpgEntry_Refresh()
		{
			this.txtDB.Text = "";
			this.txtHost.Text = "";
			this.txtPort.Text = "";
			this.txtProtocol.Text = "";
			this.txtSID.Text = "";
		}
/********************************************************************************/
		//save contents of Text Page
		private int tbpgText_Save()
		{
			try
			{
				TextWriter tw = new StreamWriter("tnsnames.ora",false);
				tw.Write(this.txttns.Text);
				tw.Close();	
				this.btnSave.Enabled = false;
				MessageBox.Show("Tnsnames.ora file saved successfully.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
				this.original_tnsora_contents = getTnsoraFileContents();
				this.txttns.Text = original_tnsora_contents;
				this.txttns.Invalidate();
				return 1;
			}
			catch
			{
				MessageBox.Show("Could not save the Tnsnames.ora file.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Error);
				return 0;
			}
		}
/********************************************************************************/
		//save contents of Entry Tab
		private int tbpgEntry_Save()
		{
			if((this.txtDB.Text.Length==0) || (this.txtHost.Text.Length==0) ||
				(this.txtProtocol.Text.Length==0) || (this.txtPort.Text.Length==0)
				|| (this.txtSID.Text.Length==0))
			{
				DialogResult dr = MessageBox.Show("All fields must be entered.","DB Manager",MessageBoxButtons.OK, MessageBoxIcon.Information);
				return 0;
			}
			else
			{
				try
				{
					TextWriter tw = new StreamWriter("tnsnames.ora",true);
					// write a line of text to the file
					tw.WriteLine(this.txtDB.Text + "= (DESCRIPTION =");
					tw.WriteLine("(ADDRESS =");
					tw.WriteLine("(PROTOCOL =" + this.txtProtocol.Text + ")");
					tw.WriteLine("(HOST =" + this.txtHost.Text +")");
					tw.WriteLine("(PORT =" + this.txtPort.Text + ")");
					tw.WriteLine(")");
					tw.WriteLine("CONNECT_DATA =");
					tw.WriteLine("(SID =" + this.txtSID.Text + ")");
					tw.WriteLine(")");
					tw.WriteLine("");
					// close the stream
					tw.Close();		
					this.btnSave.Enabled = false;

					this.original_tnsora_contents = getTnsoraFileContents();
					this.txttns.Text = original_tnsora_contents;
					this.txttns.Invalidate();

					MessageBox.Show("Entry added to Tnsnames.ora file.","DB Manager",MessageBoxButtons.OK, MessageBoxIcon.Information);
					return 1;
				}
				catch
				{
					MessageBox.Show("Could not add entry to the Tnsnames.ora file.","DB Manager",MessageBoxButtons.OK, MessageBoxIcon.Error);
					return 0;
				}
			}
		}
/********************************************************************************/
		private void txttns_TextChanged(object sender, System.EventArgs e)
		{
			this.btnSave.Enabled = true;
		}
/********************************************************************************/
		private void txtDB_TextChanged(object sender, System.EventArgs e)
		{
			this.btnSave.Enabled = true;
		}
/********************************************************************************/
		private void txtHost_TextChanged(object sender, System.EventArgs e)
		{
			this.btnSave.Enabled = true;
		}
/********************************************************************************/
		private void txtProtocol_TextChanged(object sender, System.EventArgs e)
		{
			this.btnSave.Enabled = true;
		}
/********************************************************************************/
		private void txtPort_TextChanged(object sender, System.EventArgs e)
		{
			this.btnSave.Enabled = true;
		}
/********************************************************************************/
		private void txtSID_TextChanged(object sender, System.EventArgs e)
		{
			this.btnSave.Enabled = true;
		}
/********************************************************************************/
		private void tnsTab_KeyUp(object sender, KeyEventArgs ke)
		{
			if (ke.KeyData == Keys.Escape)
				this.Hide();
		}
/********************************************************************************/
		private void btnClear_Click(object sender, System.EventArgs e)
		{
			if (this.tnsTab.SelectedTab.Text.Equals("tnsnames.ora"))
			{
				this.txttns.Text = "";
			}
			else if (this.tnsTab.SelectedTab.Text.Equals("New Entry"))
			{
				this.txtDB.Text = "";
				this.txtHost.Text = "";
				this.txtPort.Text= "";
				this.txtProtocol.Text = "";
				this.txtSID.Text = "";
				this.btnSave.Enabled = false;
			}
		}
/********************************************************************************/
	}
/********************************************************************************/
}
/********************************************************************************/