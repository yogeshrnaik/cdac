/***************************************************************************/
using System;
using System.Data;
using System.Windows.Forms;
/***************************************************************************/
namespace DBManager.classes
{
/***************************************************************************/
	/// <summary>
	/// Summary description for User.
	/// </summary>
/***************************************************************************/
	[Serializable]
	public class User
	{
/***************************************************************************/
		public string username;
		public string password;
		public string created_by;
		public DateTime created_on;
		public DataTable object_priviledges;
/***************************************************************************/
		public User(string uname, string password, string created_by, System.DateTime created_on)
		{
			this.username = uname;
			this.password = password;
			this.created_by = created_by;
			this.created_on = created_on;
			object_priviledges = new DataTable();
		}
/***************************************************************************/
	}
/***************************************************************************/
}
/***************************************************************************/
