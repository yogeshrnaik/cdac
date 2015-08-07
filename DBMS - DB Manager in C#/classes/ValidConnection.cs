/***************************************************************************/
using System;
/***************************************************************************/
namespace DBManager.classes
{
	/// <summary>
	/// ValidConnection class stores a valid combination of 
	/// username, password and database name
	/// </summary>
/***************************************************************************/
	public class ValidConnection
	{
/***************************************************************************/
		public string username;
		public string password;
		public string database;
/***************************************************************************/
		public ValidConnection(string uname, string pwd, string db)
		{
			this.username = uname;
			this.password = pwd;
			this.database = db;
		}
/***************************************************************************/
	}
/***************************************************************************/
}
/***************************************************************************/