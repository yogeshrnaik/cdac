/***************************************************************************/
using System;
using System.Collections;

using DBManager.classes;
/***************************************************************************/
namespace DBManager.classes
{
/***************************************************************************/
	/// <summary>
	/// Summary description for ConnectionTreeNode.
	/// </summary>
/***************************************************************************/
	public class SqlQueries
	{
		/***************************************************************************/
		string[] queries;
		static int counter=0;
		static int pointer=0;
		static int start_end_delimeter=0;
		ArrayList sql_cols;
		ArrayList sql_tables;
		ArrayList sql_where_cond;
		//arraylist containing all open forms for this connection
		/***************************************************************************/
		public SqlQueries()
		{
			queries = new string[20];
		}
		/***************************************************************************/
		public string getQuery(int index)
		{
			return queries[index];
		}
		/***************************************************************************/
		public void setQuery(string q)
		{
			if (counter > 0 )
			{
				int checkPrev = counter - 1;
				if (queries[checkPrev].Equals(q))
				{
					;
				}
				else
				{
					queries[counter] = q;
					counter++;
				}
			}
			else
			{
				queries[counter] = q;
				counter++;
			}
		}
        /***************************************************************************/
		public int getStartEndMarker()
		{
			if (start_end_delimeter>=0) 
			{
				return start_end_delimeter;
			}
			else 
			{
				start_end_delimeter=0;
				return start_end_delimeter;
			}
		}
		/***************************************************************************/
		public void nextStartEndMarker()
		{
			start_end_delimeter++;
		}
		public void prevStartEndMarker()
		{
			start_end_delimeter--;
			if (start_end_delimeter<0)
			{
				start_end_delimeter=0;
			}
		}
		/***************************************************************************/
		public int checkSpace()
		{
			if (counter < 19) 
			{
				return 0;
			}
			else
			{
				return 1;
			}
		}
		public int getCounter()
		{
			return counter;
		}
		public void setNext()
		{
			pointer++;
		}
		public void setPrev()
		{
			pointer--;
			if (pointer<0)
			{
				pointer=0;
			}
		}
		public int getMarker()
		{
			return pointer;
		}
		public void parseSqlQuery(string query)
		{
			sql_cols = new ArrayList(10);
			sql_tables = new ArrayList(10);
			sql_where_cond = new ArrayList(10);
			bool select_flag=false;
			bool table_flag=false;
			bool where_condn_flag=false;
			StringTokenizer st = new StringTokenizer(query,""); //If an empty delimiter is given, automatically uses " " (space).
			while (st.HasMoreTokens())
			{
				string tok = st.NextToken();
				if (tok != null)
				{
					if ( tok.ToLower().Equals("select"))
					{
						select_flag=true;
						tok = st.NextToken();
					}
					else
					{
						if ( tok.ToLower().Equals("from"))
						{
							select_flag=false;
							table_flag=true;
							tok = st.NextToken();
						}
						else
						{
							if ( tok.ToLower().Equals("where"))
							{
								table_flag=false;
								where_condn_flag=true;
								tok = st.NextToken();
							}	
						}
					}
				}
				if(select_flag)
				{
					sql_cols.Add(tok);
					sql_cols.TrimToSize();
				}
				else
				{
					if(table_flag)
					{
						sql_tables.Add(tok);
						sql_tables.TrimToSize();
					}
					else
					{
						if(where_condn_flag)
						{
							sql_where_cond.Add(tok);
							sql_where_cond.TrimToSize();
						}
					}
				}
			}
		}
		public ArrayList getSqlColumns()
		{
			return sql_cols;
		}
		public ArrayList getSqlTables()
		{
			return sql_tables;
		}
		public ArrayList getSqlWhereConditions()
		{
			return sql_where_cond;						
		}
	}
}