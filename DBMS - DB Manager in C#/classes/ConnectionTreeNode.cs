/***************************************************************************/
using System;
using System.Windows.Forms;
using System.Collections;

using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
/***************************************************************************/
namespace DBManager.classes
{
/***************************************************************************/
	/// <summary>
	/// Summary description for ConnectionTreeNode.
	/// </summary>
/***************************************************************************/
	public class ConnectionTreeNode : TreeNode
	{
/***************************************************************************/
		//flag for connection status
		public bool isConnected;
		
		public string database_name;
		public string username;
		public string password;
		
		public Database database;
		//Hashtable containing all open forms for this connection
		public Hashtable myWindows;
/***************************************************************************/
//		public ConnectionTreeNode() : base()
//		{
//			isConnected = true;
//			tabWindows = new TabControl();
//		}
/***************************************************************************/
		public ConnectionTreeNode(string db_name, string username, string password, int imgIndex, int selImgIndex,
									bool isConnected)
								: base(username + "@" + db_name, imgIndex, selImgIndex)
		{
			init_database(db_name, username, password);
			this.isConnected = isConnected;
		}
/***************************************************************************/
		public ConnectionTreeNode(string db_name, string username, string password, int imgIndex, int selImgIndex,
									TreeNode[] childs, bool isConnected)
			: base(username + "@" + db_name, imgIndex, selImgIndex)
		{
			init_database(db_name, username, password);
			this.isConnected = isConnected;
			this.Nodes.AddRange(childs);
			
			//create one tab page for SQL Window
			string  strTabPageName = this.Text + " - " + "SQL Window";
			TabPage tp = new TabPage(strTabPageName);
			tp.ImageIndex = MyImageList.getImgList().getImageIndex("SQL_WINDOW");
			this.myWindows.Add(tp.Text, tp);
		}
/***************************************************************************/
		//read from file and create database object here or create a new database object 
		public void init_database(string db_name, string username, string password)
		{
			this.database_name = db_name;
			this.username = username;
			this.password = password;
			isConnected = true;
			//read from file and create database object here or create a new database object 
			try 
			{
				IFormatter formatter = new BinaryFormatter();
				Stream stream = new FileStream(database_name + ".bin", FileMode.Open, FileAccess.Read, FileShare.Read);
				database = (Database) formatter.Deserialize(stream);
				stream.Close();
			}
			catch
			{
				//MessageBox.Show("exception");
				database = null;
          }
			if (database == null)
			{
				//MessageBox.Show("database not de-serialized");
				database = new Database(database_name);
			}
			myWindows = new Hashtable();
		}
/***************************************************************************/
		//save database object in a file 
		public bool saveDatabase()
		{
			//serialize the database object
			try 
			{
				IFormatter formatter = new BinaryFormatter();
				Stream stream = new FileStream(database_name+".bin", FileMode.Create, FileAccess.Write, FileShare.None);
				formatter.Serialize(stream, database);
				stream.Close();
				return true;
			}
			catch
			{
				return false;
			}
		}
/***************************************************************************/
		public int getWindowCount(string winName)
		{
			int count = 0;
			IDictionaryEnumerator e = myWindows.GetEnumerator();
			for (int i=0; i < myWindows.Keys.Count; i++)
			{
				e.MoveNext();
				TabPage tp = (TabPage)myWindows[e.Key];
				if (tp.Text.ToUpper().IndexOf(winName.ToUpper()) >= 0)
				{
					count++;
				}
			}
			return count;
		}
/***************************************************************************/
		public TabPage getWindowByName(string winName)
		{
			IDictionaryEnumerator e = myWindows.GetEnumerator();
			for (int i=0; i < myWindows.Keys.Count; i++)
			{
				e.MoveNext();
				TabPage tp = (TabPage)myWindows[e.Key];
				if (tp.Text.ToUpper().Equals(winName.ToUpper()))
				{
					return tp;
				}
			}
			return null;
		}
/***************************************************************************/
		public void addWindow(TabPage tpg)
		{
			if (myWindows[tpg.Text] == null)
			{
				myWindows.Add(tpg.Text, tpg);
			}
		}
/***************************************************************************/
		public void closeWindow(TabPage tpg)
		{
			tpg.Hide();
			myWindows.Remove(tpg.Text);
			tpg.Dispose();
		}
/***************************************************************************/
		public void closeAllWindows()
		{
			myWindows = new Hashtable();
		}
/***************************************************************************/
/***************************************************************************/
/***************************************************************************/
	}
/***************************************************************************/
}
/***************************************************************************/