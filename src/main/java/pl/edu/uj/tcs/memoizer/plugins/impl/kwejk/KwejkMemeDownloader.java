package pl.edu.uj.tcs.memoizer.plugins.impl.kwejk;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import pl.edu.uj.tcs.memoizer.plugins.EViewType;
import pl.edu.uj.tcs.memoizer.plugins.IPluginFactory;
import pl.edu.uj.tcs.memoizer.plugins.Meme;
import pl.edu.uj.tcs.memoizer.plugins.common.CommonMemeDownloader;

public class KwejkMemeDownloader extends CommonMemeDownloader{

	@Override
	protected Elements extractMemeNodes(Document kwejkPageSource) {
		 Elements result = new Elements();
         
         if(kwejkPageSource == null)
                 return result;
         
         Elements kwejkiDiv = kwejkPageSource.select("div.mediaPair");
         for(Element elem : kwejkiDiv){
                 Elements kwejk = elem.select("div.shot[title]");
                 result.addAll(kwejk);
         }
         
         return result;
	}

	@Override
	protected List<Meme> extractMemesFromNodes(Elements memeNodes,
			EViewType viewType, IPluginFactory pluginFactory) {
		List<Meme> lst = new ArrayList<Meme>();
        
        for(Element meme : memeNodes){
                try{
                        String description = "";
                        
                        Element desc = meme.select("div.desc > div.tooltip-title-container > div.tooltip-title-left-corner > div.tooltip-title").first();
                        String title = extractTitleFromDesc(desc);
                        
                        Element media = meme.select("div.content > div.media").first();
                        Element picLink = media.select("a.mOUrl[href]").first();
                        URL pageLink = extractPageLinkFromATag(picLink);
                        
                        Element image = picLink.select("img[src]").first();
                        URL imageLink = extractImageLinkFromImgTag(image);
                        int width = extractWidthFromImgTag(image);
                        int heigth = extractHeightFromImgTag(image);
                        
                        if(imageLink != null){
        					Meme newMeme = new Meme(imageLink, pageLink, title, description, width, heigth, null, pluginFactory);
        					newMeme.setId(new Random().nextInt());
        					lst.add(newMeme);
        				}
                } catch(Exception e){}
        }
        
        return lst;
	}
	
	private URL extractPageLinkFromATag(Element aTagElement){
        try{
                return new URL("http://www.kwejk.pl" + aTagElement.attr("href"));
        }catch(Exception e){}
        return null;
	}
	
	private String extractTitleFromDesc(Element desc){
        try{
                return desc.text();
        } catch(Exception e){}
        return "";
}
}
