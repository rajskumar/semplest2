package semplest.keywords.javautils;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.io.Console;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import semplest.keywords.properties.*;

// Uses Lucene to search the Dmoz description database
public class DmozLucene
{

	private static final Logger logger = Logger.getLogger(DmozLucene.class);
	IndexWriter w;
	StandardAnalyzer analyzer;
	Directory index;

	// Ctr --------------
	public DmozLucene()
	{
		// Specify the analyzer for tokenizing text.
		// (The same analyzer should be used for indexing and searching)
		try
		{
			logger.info("DmozLucene()");
			analyzer = new StandardAnalyzer(Version.LUCENE_35);

			// 1. create the index
			index = new RAMDirectory();

			IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_35, analyzer);
			w = new IndexWriter(index, config);
		}
		catch (Exception e)
		{

			logger.error(e.getMessage(), e);
		}
	}

	// --------
	// add the description data to the database
	void add(String cat, String desc)
	{
		try
		{
			Document doc = new Document();
			doc.add(new Field("cat", cat, Field.Store.YES, Field.Index.ANALYZED));
			doc.add(new Field("desc", desc, Field.Store.YES, Field.Index.ANALYZED));
			w.addDocument(doc);
		}
		catch (Exception e)
		{
			logger.error("Problem", e);
		}
	}

	// done adding words
	void done()
	{
		try
		{
			w.close();
		}
		catch (Exception e)
		{
			logger.error("Problem", e);
		}
	}

	// ---------
	public String[] search(String qs)
	{
		String[] res = new String[0];
		try
		{
			res = search(qs, 10);
		}
		catch (Exception e)
		{
			logger.error("Problem", e);
		}
		return res;
	}

	// -----
	public String[] search(String qs, int hitsPerPage)
	{

		String[] res = new String[0];
		try
		{
			// "desc" is the default field to search
			Query qp = new QueryParser(Version.LUCENE_35, "desc", analyzer).parse(qs);

			// Search
			IndexSearcher searcher = new IndexSearcher(index, true);
			TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
			searcher.search(qp, collector);
			ScoreDoc[] hits = collector.topDocs().scoreDocs;

			res = new String[hits.length];
			for (int i = 0; i < hits.length; ++i)
			{
				int docId = hits[i].doc;
				Document d = searcher.doc(docId);
				res[i] = d.get("cat");
			}
			searcher.close();
		}
		catch (Exception e)
		{
			logger.error("Problem", e);
		}
		return res;
	}

	// Static Helpers ---------------
	public static void loadDesc(DmozLucene dl, String f)
	{
		Map<String, String> map = ioUtils.readDescs(f);
		for (Map.Entry<String, String> e : map.entrySet())
			dl.add(e.getKey(), e.getValue());
		dl.done();
	}

	public static void loadDescDB(DmozLucene dl) throws Exception
	{
		Map<String, String> map = keywordb.getAll("descs");
		for (Map.Entry<String, String> e : map.entrySet())
			dl.add(e.getKey(), e.getValue());
		dl.done();
	}

	public static void interactiveTest()
	{
		final String dfile = ProjectProperties.lucenedfile;
		// final String dfile = "dmoz/all/hCounts.new";

		DmozLucene dl = new DmozLucene();
		loadDesc(dl, dfile);
		Console c = System.console();
		while (true)
		{
			c.printf(" > ");
			String q = c.readLine();
			String sq = TextUtils.stemvString(q);
			if (sq.length() > 0)
			{
				String[] res = dl.search(sq);
				for (String re : res)
					c.printf("%s\n", re);
			}
		}
	}

	// -------------------------------------------------------------------
	// [Important Note:] Make sure that text and the query are *both* either
	// o stemmed
	// o or unstemmed
	// Search will not work if one is stemmed and the other not.

	// -------------------------------
	public static void main(String[] args) throws IOException, ParseException
	{
		interactiveTest();
	}
}
