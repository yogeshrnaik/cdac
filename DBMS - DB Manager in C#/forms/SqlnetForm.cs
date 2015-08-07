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
/********************************************************************************/
	/// <summary>
	/// Summary description for frmSqlnet.
	/// </summary>
/********************************************************************************/
	public class SqlnetForm : System.Windows.Forms.Form
	{
		private System.Windows.Forms.Button btnSave;
		private System.Windows.Forms.Button btnCancel;
		private System.Windows.Forms.GroupBox groupBox1;
		private System.Windows.Forms.TextBox txtLogFile;
		private System.Windows.Forms.TextBox txtTraceFile;
		private System.Windows.Forms.NumericUpDown nUpDnFileLn;
		private System.Windows.Forms.NumericUpDown nUpDnFileNo;
		private System.Windows.Forms.ComboBox cboxTraceLvl;
		private System.Windows.Forms.Label lblLogFile;
		private System.Windows.Forms.Label lblFileLn;
		private System.Windows.Forms.Label lblTraceFile;
		private System.Windows.Forms.Label lblExpTm;
		private System.Windows.Forms.CheckBox chkbTS;
		private System.Windows.Forms.CheckBox chkbUC;
		private System.Windows.Forms.CheckBox chkbDS;
		private System.Windows.Forms.Label lblFileNo;
		private System.Windows.Forms.Label lblTraceLvl;
		private System.Windows.Forms.Label lblTS;
		private System.Windows.Forms.Label lblUC;
		private System.Windows.Forms.Label lblDS;
		private System.Windows.Forms.NumericUpDown nUpDnexptm;
		private System.Windows.Forms.Button btnLogDir;
		private System.Windows.Forms.Button btnTraceDir;
		private System.Windows.Forms.CheckBox chkSaveInFile;
/********************************************************************************/
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.Container components = null;
/********************************************************************************/
		public SqlnetForm()
		{
			//
			// Required for Windows Form Designer support
			//
			InitializeComponent();
			this.ShowInTaskbar = false;

			//
			// TODO: Add any constructor code after InitializeComponent call
			//
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
			this.btnSave = new System.Windows.Forms.Button();
			this.btnCancel = new System.Windows.Forms.Button();
			this.groupBox1 = new System.Windows.Forms.GroupBox();
			this.btnTraceDir = new System.Windows.Forms.Button();
			this.btnLogDir = new System.Windows.Forms.Button();
			this.lblDS = new System.Windows.Forms.Label();
			this.lblUC = new System.Windows.Forms.Label();
			this.lblTS = new System.Windows.Forms.Label();
			this.lblTraceLvl = new System.Windows.Forms.Label();
			this.lblFileNo = new System.Windows.Forms.Label();
			this.chkbDS = new System.Windows.Forms.CheckBox();
			this.chkbUC = new System.Windows.Forms.CheckBox();
			this.chkbTS = new System.Windows.Forms.CheckBox();
			this.cboxTraceLvl = new System.Windows.Forms.ComboBox();
			this.nUpDnFileNo = new System.Windows.Forms.NumericUpDown();
			this.nUpDnFileLn = new System.Windows.Forms.NumericUpDown();
			this.nUpDnexptm = new System.Windows.Forms.NumericUpDown();
			this.lblFileLn = new System.Windows.Forms.Label();
			this.lblTraceFile = new System.Windows.Forms.Label();
			this.lblExpTm = new System.Windows.Forms.Label();
			this.lblLogFile = new System.Windows.Forms.Label();
			this.txtTraceFile = new System.Windows.Forms.TextBox();
			this.txtLogFile = new System.Windows.Forms.TextBox();
			this.chkSaveInFile = new System.Windows.Forms.CheckBox();
			this.groupBox1.SuspendLayout();
			((System.ComponentModel.ISupportInitialize)(this.nUpDnFileNo)).BeginInit();
			((System.ComponentModel.ISupportInitialize)(this.nUpDnFileLn)).BeginInit();
			((System.ComponentModel.ISupportInitialize)(this.nUpDnexptm)).BeginInit();
			this.SuspendLayout();
			// 
			// btnSave
			// 
			this.btnSave.Location = new System.Drawing.Point(304, 344);
			this.btnSave.Name = "btnSave";
			this.btnSave.TabIndex = 16;
			this.btnSave.Text = "Save";
			this.btnSave.Click += new System.EventHandler(this.btnSave_Click);
			// 
			// btnCancel
			// 
			this.btnCancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
			this.btnCancel.Location = new System.Drawing.Point(392, 344);
			this.btnCancel.Name = "btnCancel";
			this.btnCancel.Size = new System.Drawing.Size(80, 24);
			this.btnCancel.TabIndex = 17;
			this.btnCancel.Text = "Cancel";
			this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
			// 
			// groupBox1
			// 
			this.groupBox1.Controls.Add(this.btnTraceDir);
			this.groupBox1.Controls.Add(this.btnLogDir);
			this.groupBox1.Controls.Add(this.lblDS);
			this.groupBox1.Controls.Add(this.lblUC);
			this.groupBox1.Controls.Add(this.lblTS);
			this.groupBox1.Controls.Add(this.lblTraceLvl);
			this.groupBox1.Controls.Add(this.lblFileNo);
			this.groupBox1.Controls.Add(this.chkbDS);
			this.groupBox1.Controls.Add(this.chkbUC);
			this.groupBox1.Controls.Add(this.chkbTS);
			this.groupBox1.Controls.Add(this.cboxTraceLvl);
			this.groupBox1.Controls.Add(this.nUpDnFileNo);
			this.groupBox1.Controls.Add(this.nUpDnFileLn);
			this.groupBox1.Controls.Add(this.nUpDnexptm);
			this.groupBox1.Controls.Add(this.lblFileLn);
			this.groupBox1.Controls.Add(this.lblTraceFile);
			this.groupBox1.Controls.Add(this.lblExpTm);
			this.groupBox1.Controls.Add(this.lblLogFile);
			this.groupBox1.Controls.Add(this.txtTraceFile);
			this.groupBox1.Controls.Add(this.txtLogFile);
			this.groupBox1.Location = new System.Drawing.Point(8, 16);
			this.groupBox1.Name = "groupBox1";
			this.groupBox1.Size = new System.Drawing.Size(472, 320);
			this.groupBox1.TabIndex = 18;
			this.groupBox1.TabStop = false;
			this.groupBox1.Text = "SQLNET.ORA Parameters";
			// 
			// btnTraceDir
			// 
			this.btnTraceDir.Location = new System.Drawing.Point(440, 88);
			this.btnTraceDir.Name = "btnTraceDir";
			this.btnTraceDir.Size = new System.Drawing.Size(24, 23);
			this.btnTraceDir.TabIndex = 32;
			this.btnTraceDir.Text = "...";
			this.btnTraceDir.Click += new System.EventHandler(this.btnTraceDir_Click);
			// 
			// btnLogDir
			// 
			this.btnLogDir.Location = new System.Drawing.Point(440, 24);
			this.btnLogDir.Name = "btnLogDir";
			this.btnLogDir.Size = new System.Drawing.Size(24, 24);
			this.btnLogDir.TabIndex = 30;
			this.btnLogDir.Text = "...";
			this.btnLogDir.Click += new System.EventHandler(this.button1_Click);
			// 
			// lblDS
			// 
			this.lblDS.Location = new System.Drawing.Point(24, 288);
			this.lblDS.Name = "lblDS";
			this.lblDS.Size = new System.Drawing.Size(176, 16);
			this.lblDS.TabIndex = 29;
			this.lblDS.Text = "USE_DIDICATED_SERVER";
			// 
			// lblUC
			// 
			this.lblUC.Location = new System.Drawing.Point(24, 256);
			this.lblUC.Name = "lblUC";
			this.lblUC.Size = new System.Drawing.Size(176, 16);
			this.lblUC.TabIndex = 28;
			this.lblUC.Text = "TRACE_UNIQUE_CLIENT";
			// 
			// lblTS
			// 
			this.lblTS.Location = new System.Drawing.Point(24, 224);
			this.lblTS.Name = "lblTS";
			this.lblTS.Size = new System.Drawing.Size(176, 16);
			this.lblTS.TabIndex = 27;
			this.lblTS.Text = "TRACE_TIMESTAMP_CLIENT";
			// 
			// lblTraceLvl
			// 
			this.lblTraceLvl.Location = new System.Drawing.Point(24, 192);
			this.lblTraceLvl.Name = "lblTraceLvl";
			this.lblTraceLvl.Size = new System.Drawing.Size(176, 16);
			this.lblTraceLvl.TabIndex = 26;
			this.lblTraceLvl.Text = "TRACE_LEVEL_CLIENT";
			// 
			// lblFileNo
			// 
			this.lblFileNo.Location = new System.Drawing.Point(24, 160);
			this.lblFileNo.Name = "lblFileNo";
			this.lblFileNo.Size = new System.Drawing.Size(176, 16);
			this.lblFileNo.TabIndex = 25;
			this.lblFileNo.Text = "TRACE_FILENO_CLIENT";
			// 
			// chkbDS
			// 
			this.chkbDS.Location = new System.Drawing.Point(216, 288);
			this.chkbDS.Name = "chkbDS";
			this.chkbDS.Size = new System.Drawing.Size(16, 16);
			this.chkbDS.TabIndex = 11;
			// 
			// chkbUC
			// 
			this.chkbUC.Location = new System.Drawing.Point(216, 256);
			this.chkbUC.Name = "chkbUC";
			this.chkbUC.Size = new System.Drawing.Size(16, 16);
			this.chkbUC.TabIndex = 10;
			// 
			// chkbTS
			// 
			this.chkbTS.Location = new System.Drawing.Point(216, 224);
			this.chkbTS.Name = "chkbTS";
			this.chkbTS.Size = new System.Drawing.Size(16, 16);
			this.chkbTS.TabIndex = 9;
			// 
			// cboxTraceLvl
			// 
			this.cboxTraceLvl.Items.AddRange(new object[] {
															  "Yes",
															  "No"});
			this.cboxTraceLvl.Location = new System.Drawing.Point(216, 192);
			this.cboxTraceLvl.Name = "cboxTraceLvl";
			this.cboxTraceLvl.Size = new System.Drawing.Size(104, 21);
			this.cboxTraceLvl.TabIndex = 8;
			// 
			// nUpDnFileNo
			// 
			this.nUpDnFileNo.Location = new System.Drawing.Point(216, 160);
			this.nUpDnFileNo.Maximum = new System.Decimal(new int[] {
																		10,
																		0,
																		0,
																		0});
			this.nUpDnFileNo.Name = "nUpDnFileNo";
			this.nUpDnFileNo.Size = new System.Drawing.Size(56, 20);
			this.nUpDnFileNo.TabIndex = 7;
			// 
			// nUpDnFileLn
			// 
			this.nUpDnFileLn.Location = new System.Drawing.Point(216, 128);
			this.nUpDnFileLn.Name = "nUpDnFileLn";
			this.nUpDnFileLn.Size = new System.Drawing.Size(56, 20);
			this.nUpDnFileLn.TabIndex = 6;
			// 
			// nUpDnexptm
			// 
			this.nUpDnexptm.Location = new System.Drawing.Point(216, 56);
			this.nUpDnexptm.Name = "nUpDnexptm";
			this.nUpDnexptm.Size = new System.Drawing.Size(56, 20);
			this.nUpDnexptm.TabIndex = 3;
			// 
			// lblFileLn
			// 
			this.lblFileLn.Location = new System.Drawing.Point(24, 128);
			this.lblFileLn.Name = "lblFileLn";
			this.lblFileLn.Size = new System.Drawing.Size(176, 16);
			this.lblFileLn.TabIndex = 17;
			this.lblFileLn.Text = "TRACE_FILELN_CLIENT";
			// 
			// lblTraceFile
			// 
			this.lblTraceFile.Location = new System.Drawing.Point(24, 96);
			this.lblTraceFile.Name = "lblTraceFile";
			this.lblTraceFile.Size = new System.Drawing.Size(176, 16);
			this.lblTraceFile.TabIndex = 16;
			this.lblTraceFile.Text = "TRACE_FILE_CLIENT";
			// 
			// lblExpTm
			// 
			this.lblExpTm.Location = new System.Drawing.Point(24, 64);
			this.lblExpTm.Name = "lblExpTm";
			this.lblExpTm.Size = new System.Drawing.Size(176, 16);
			this.lblExpTm.TabIndex = 14;
			this.lblExpTm.Text = "SQLNET.EXPIRE_TIME";
			// 
			// lblLogFile
			// 
			this.lblLogFile.Location = new System.Drawing.Point(24, 32);
			this.lblLogFile.Name = "lblLogFile";
			this.lblLogFile.Size = new System.Drawing.Size(176, 16);
			this.lblLogFile.TabIndex = 13;
			this.lblLogFile.Text = "LOG_FILE_CLIENT";
			// 
			// txtTraceFile
			// 
			this.txtTraceFile.Location = new System.Drawing.Point(216, 88);
			this.txtTraceFile.Name = "txtTraceFile";
			this.txtTraceFile.Size = new System.Drawing.Size(216, 20);
			this.txtTraceFile.TabIndex = 5;
			this.txtTraceFile.Text = "";
			// 
			// txtLogFile
			// 
			this.txtLogFile.Location = new System.Drawing.Point(216, 24);
			this.txtLogFile.Name = "txtLogFile";
			this.txtLogFile.Size = new System.Drawing.Size(216, 20);
			this.txtLogFile.TabIndex = 2;
			this.txtLogFile.Text = "";
			// 
			// chkSaveInFile
			// 
			this.chkSaveInFile.Location = new System.Drawing.Point(16, 344);
			this.chkSaveInFile.Name = "chkSaveInFile";
			this.chkSaveInFile.Size = new System.Drawing.Size(264, 24);
			this.chkSaveInFile.TabIndex = 12;
			this.chkSaveInFile.Text = "Make this changes Permanent to sqlnet.ora file";
			// 
			// SqlnetForm
			// 
			this.AcceptButton = this.btnSave;
			this.AutoScaleBaseSize = new System.Drawing.Size(5, 13);
			this.CancelButton = this.btnCancel;
			this.ClientSize = new System.Drawing.Size(498, 383);
			this.Controls.Add(this.chkSaveInFile);
			this.Controls.Add(this.groupBox1);
			this.Controls.Add(this.btnCancel);
			this.Controls.Add(this.btnSave);
			this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
			this.MaximizeBox = false;
			this.MinimizeBox = false;
			this.Name = "SqlnetForm";
			this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
			this.Text = "Sqlnet.ora";
			this.groupBox1.ResumeLayout(false);
			((System.ComponentModel.ISupportInitialize)(this.nUpDnFileNo)).EndInit();
			((System.ComponentModel.ISupportInitialize)(this.nUpDnFileLn)).EndInit();
			((System.ComponentModel.ISupportInitialize)(this.nUpDnexptm)).EndInit();
			this.ResumeLayout(false);

		}
		#endregion
/********************************************************************************/
		private void btnCancel_Click(object sender, System.EventArgs e)
		{
			this.Close();
		}
/********************************************************************************/
		private void btnSave_Click(object sender, System.EventArgs e)
		{
			if (chkSaveInFile.Checked)
			{
				try 
				{
					TextWriter tw = new StreamWriter("sqlnet.ora");

					// write a line of text to the file
					tw.WriteLine(this.lblLogFile.Text + "=" + this.txtLogFile.Text);
					tw.WriteLine(this.lblExpTm.Text + "=" + this.nUpDnexptm.Value);
					tw.WriteLine(this.lblTraceFile.Text + "=" + this.txtTraceFile.Text);
					tw.WriteLine(this.lblFileLn.Text + "=" + this.nUpDnFileLn.Value);
					tw.WriteLine(this.lblFileNo.Text + "=" + this.nUpDnFileNo.Value);
					tw.WriteLine(this.lblTraceLvl.Text + "=" + this.cboxTraceLvl.Text);
					tw.WriteLine(this.lblTS.Text + "=" + this.chkbTS.Checked);
					tw.WriteLine(this.lblUC.Text + "=" + this.chkbUC.Checked);
					tw.WriteLine(this.lblDS.Text + "=" + this.chkbDS.Checked);
					// close the stream
					tw.Close();
					this.Close();
					MessageBox.Show("Sqlnet.ora file saved successfully.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
				}
				catch
				{
					MessageBox.Show("Error while saving Sqlnet.ora file.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Error);
				}
			}
			else
			{
				MessageBox.Show("Sqlnet.ora file parameters saved successfully.", "DB Manager", MessageBoxButtons.OK, MessageBoxIcon.Information);
			}
		}
/********************************************************************************/
		private void button1_Click(object sender, System.EventArgs e)
		{
			OpenFileDialog ofd;
			ofd = new OpenFileDialog();
			ofd.InitialDirectory = "E:\\UserData";
			ofd.RestoreDirectory = true;
			if( ofd.ShowDialog() == DialogResult.OK )
			{
				this.txtLogFile.Text = ofd.FileName;
			}
		}
/********************************************************************************/
		private void btnTraceDir_Click(object sender, System.EventArgs e)
		{
			OpenFileDialog ofd;
			ofd = new OpenFileDialog();
			ofd.InitialDirectory = "E:\\UserData";
			if( ofd.ShowDialog() == DialogResult.OK )
			{
				this.txtTraceFile.Text = ofd.FileName;
			}
		}
/********************************************************************************/
	}
/********************************************************************************/
}
/********************************************************************************/
