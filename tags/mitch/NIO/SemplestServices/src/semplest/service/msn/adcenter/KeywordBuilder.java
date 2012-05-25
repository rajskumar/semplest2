package semplest.service.msn.adcenter;

import com.microsoft.adcenter.v8.Bid;
import com.microsoft.adcenter.v8.Keyword;
import com.microsoft.adcenter.v8.KeywordEditorialStatus;
import com.microsoft.adcenter.v8.KeywordStatus;

public class KeywordBuilder {
	private final Bid _broadMatchBid;
	private final Bid _contentMatchBid;
	private final KeywordEditorialStatus _editorialStatus;
	private final Bid _exactMatchBid;
	private final Long _id;
	private final String[] _negativeKeywords;
	private final String _param1;
	private final String _param2;
	private final String _param3;
	private final Bid _phraseMatchBid;
	private final KeywordStatus _status;
	private final String _text;
	
	KeywordBuilder(Bid broadMatchBid, Bid contentMatchBid, KeywordEditorialStatus editorialStatus, Bid exactMatchBid, Long id, String[] negativeKeywords, String param1, String param2, String param3, Bid phraseMatchBid, KeywordStatus status, String text) {
		this._broadMatchBid = broadMatchBid;
		this._contentMatchBid = contentMatchBid;
		this._editorialStatus = editorialStatus;
		this._exactMatchBid = exactMatchBid;
		this._id = id;
		this._negativeKeywords = negativeKeywords;
		this._param1 = param1;
		this._param2 = param2;
		this._param3 = param3;
		this._phraseMatchBid = phraseMatchBid;
		this._status = status;
		this._text = text;
	}
	
	public static KeywordBuilder create() {
		return new KeywordBuilder(new Bid(.50), null, null, new Bid(1.50), null, null, null, null, null, null, null, "mittens");
	}
	
	public KeywordBuilder withBroadMatchBid(Bid broadMatchBid) {
		return new KeywordBuilder(broadMatchBid, this._contentMatchBid, this._editorialStatus, this._exactMatchBid, this._id, this._negativeKeywords, this._param1, this._param2, this._param3, this._phraseMatchBid, this._status, this._text);
	}
	
	public KeywordBuilder withContentMatchBid(Bid contentMatchBid) {
		return new KeywordBuilder(this._broadMatchBid, contentMatchBid, this._editorialStatus, this._exactMatchBid, this._id, this._negativeKeywords, this._param1, this._param2, this._param3, this._phraseMatchBid, this._status, this._text);
	}
	
	public KeywordBuilder with(KeywordEditorialStatus editorialStatus) {
		return new KeywordBuilder(this._broadMatchBid, this._contentMatchBid, editorialStatus, this._exactMatchBid, this._id, this._negativeKeywords, this._param1, this._param2, this._param3, this._phraseMatchBid, this._status, this._text);
	}
	
	public KeywordBuilder withExactMatchBid(Bid exactMatchBid) {
		return new KeywordBuilder(this._broadMatchBid, this._contentMatchBid, this._editorialStatus, exactMatchBid, this._id, this._negativeKeywords, this._param1, this._param2, this._param3, this._phraseMatchBid, this._status, this._text);
	}
	
	public KeywordBuilder with(Long id) {
		return new KeywordBuilder(this._broadMatchBid, this._contentMatchBid, this._editorialStatus, this._exactMatchBid, id, this._negativeKeywords, this._param1, this._param2, this._param3, this._phraseMatchBid, this._status, this._text);
	}
	
	public KeywordBuilder with(String[] negativeKeywords) {
		return new KeywordBuilder(this._broadMatchBid, this._contentMatchBid, this._editorialStatus, this._exactMatchBid, this._id, negativeKeywords, this._param1, this._param2, this._param3, this._phraseMatchBid, this._status, this._text);
	}
	
	public KeywordBuilder withParam1(String param1) {
		return new KeywordBuilder(this._broadMatchBid, this._contentMatchBid, this._editorialStatus, this._exactMatchBid, this._id, this._negativeKeywords, param1, this._param2, this._param3, this._phraseMatchBid, this._status, this._text);
	}
	
	public KeywordBuilder withParam2(String param2) {
		return new KeywordBuilder(this._broadMatchBid, this._contentMatchBid, this._editorialStatus, this._exactMatchBid, this._id, this._negativeKeywords, this._param1, param2, this._param3, this._phraseMatchBid, this._status, this._text);
	}
	
	public KeywordBuilder withParam3(String param3) {
		return new KeywordBuilder(this._broadMatchBid, this._contentMatchBid, this._editorialStatus, this._exactMatchBid, this._id, this._negativeKeywords, this._param1, this._param2, param3, this._phraseMatchBid, this._status, this._text);
	}
	
	public KeywordBuilder withPhraseMatchBid(Bid phraseMatchBid) {
		return new KeywordBuilder(this._broadMatchBid, this._contentMatchBid, this._editorialStatus, this._exactMatchBid, this._id, this._negativeKeywords, this._param1, this._param2, this._param3, phraseMatchBid, this._status, this._text);
	}
	
	public KeywordBuilder with(KeywordStatus status) {
		return new KeywordBuilder(this._broadMatchBid, this._contentMatchBid, this._editorialStatus, this._exactMatchBid, this._id, this._negativeKeywords, this._param1, this._param2, this._param3, this._phraseMatchBid, status, this._text);
	}
	
	public KeywordBuilder withText(String text) {
		return new KeywordBuilder(this._broadMatchBid, this._contentMatchBid, this._editorialStatus, this._exactMatchBid, this._id, this._negativeKeywords, this._param1, this._param2, this._param3, this._phraseMatchBid, this._status, text);
	}
	
	public Keyword build() {
		return new Keyword(this._broadMatchBid, this._contentMatchBid, this._editorialStatus, this._exactMatchBid, this._id, this._negativeKeywords, this._param1, this._param2, this._param3, this._phraseMatchBid, this._status, this._text);
	}
}