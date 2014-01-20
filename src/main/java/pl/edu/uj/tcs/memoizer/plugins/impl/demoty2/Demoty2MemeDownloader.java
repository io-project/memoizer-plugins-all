package pl.edu.uj.tcs.memoizer.plugins.impl.demoty2;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import pl.edu.uj.tcs.memoizer.plugins.EViewType;
import pl.edu.uj.tcs.memoizer.plugins.IPluginFactory;
import pl.edu.uj.tcs.memoizer.plugins.Meme;
import pl.edu.uj.tcs.memoizer.plugins.common.CommonMemeDownloader;

public class Demoty2MemeDownloader extends CommonMemeDownloader{

	@Override
	protected Elements extractMemeNodes(Document demotyPageSource) {
		Elements result = new Elements();
		
		if(demotyPageSource == null)
			return result;
		
		Elements demotySection = demotyPageSource.select("section.demots");
		for(Element elem : demotySection){
			Elements demotivators = elem.select("div.demotivator[id]");
			result.addAll(demotivators);
		}
		
		return result;
	}

	@Override
	protected List<Meme> extractMemesFromNodes(Elements memeNodes,
			EViewType viewType, IPluginFactory pluginFactory) {
List<Meme> lst = new ArrayList<Meme>();
		
		for(Element meme : memeNodes){
			try{
				Element picLink = meme.select("a.picwrapper[href]").first();
				URL pageLink = extractPageLinkFromATag(picLink);
				
				//<input type="hidden" class="pic_id" name="pic_id" value="4247512"/>
				Integer idInput = Integer.parseInt(meme.select("input[name=pic_id]").first().attr("value"));
				
				Element image = picLink.select("img.demot[src]").first();
				URL imageLink = extractImageLinkFromImgTag(image);
				

				String fullTitle = extractTitleFromImgTag(image), title = null, description = null;
				int split = fullTitle.indexOf('–');
				System.out.println(fullTitle+" "+split);
				if(split<0)
					title = fullTitle;
				else if(split==0)
					title = fullTitle.substring(1, fullTitle.length()-1).trim();
				else if(split==fullTitle.length()-1)
					title = fullTitle.substring(0, fullTitle.length()-2).trim();
				else{
					title = fullTitle.substring(0, split-1).trim();
					description = fullTitle.substring(split+1, fullTitle.length()-1).trim();
				}
				
				
				int width = extractWidthFromImgTag(image);
				int heigth = extractHeightFromImgTag(image);
				
				if(imageLink != null){
					Meme newMeme = new Meme(imageLink, pageLink, title, description, width, heigth, null, pluginFactory);
					newMeme.setId(idInput);
					lst.add(newMeme);
				}
			} catch(Exception e){
				
			}
		}
		
		return lst;
	}
	
	private URL extractPageLinkFromATag(Element aTagElement){
		try{
			return new URL("http://www.demotywatory.pl" + aTagElement.attr("href"));
		}catch(Exception e){}
		return null;
	}

}
