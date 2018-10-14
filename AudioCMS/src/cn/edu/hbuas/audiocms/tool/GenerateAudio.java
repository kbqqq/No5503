package cn.edu.hbuas.audiocms.tool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;

public class GenerateAudio extends Thread {
    public static final String APP_ID = "11139621";
    public static final String API_KEY = "Gg1oy8x7wvtjxnTTysyXGptC";
    public static final String SECRET_KEY = "yCEFIj4zo5BNHqFqrQcMbEbwknACzXUb";
    public int id;
    public String content;
    public GenerateAudio(int id, String content) {
    	this.id = id;
    	this.content = content;
    }
    /**
     * ��ԭʼ�ַ����ָ��ָ�����ȵ��ַ����б�
     * 
     * @param inputString
     *            ԭʼ�ַ���
     * @param length
     *            ָ������
     * @return
     */
    public static List<String> getStrList(String inputString, int length) {
        int size = inputString.length() / length;
        if (inputString.length() % length != 0) {
            size += 1;
        }
        return getStrList(inputString, length, size);
    }

 /**
     * ��ԭʼ�ַ����ָ��ָ�����ȵ��ַ����б�
     * 
     * @param inputString
     *            ԭʼ�ַ���
     * @param length
     *            ָ������
     * @param size
     *            ָ���б��С
     * @return
     */
    public static List<String> getStrList(String inputString, int length,
            int size) {
        List<String> list = new ArrayList<String>();
        for (int index = 0; index < size; index++) {
            String childStr = substring(inputString, index * length,
                    (index + 1) * length);
            list.add(childStr);
        }
        return list;
    }
    /**
     * �ָ��ַ����������ʼλ�ô����ַ������ȣ����ؿ�
     * 
     * @param str
     *            ԭʼ�ַ���
     * @param f
     *            ��ʼλ��
     * @param t
     *            ����λ��
     * @return
     */
    public static String substring(String str, int f, int t) {
        if (f > str.length())
            return null;
        if (t > str.length()) {
            return str.substring(f, str.length());
        } else {
            return str.substring(f, t);
        }
    }
    public static String delHTMLTag(String htmlStr){ 
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //����script��������ʽ 
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //����style��������ʽ 
        String regEx_html="<[^>]+>"; //����HTML��ǩ��������ʽ 
        String regEx_and="&[^;]+;"; //����&****;��������ʽ 

        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(htmlStr); 
        htmlStr=m_script.replaceAll(""); //����script��ǩ 

        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style=p_style.matcher(htmlStr); 
        htmlStr=m_style.replaceAll(""); //����style��ǩ 

        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(htmlStr); 
        htmlStr=m_html.replaceAll(""); //����html��ǩ

        Pattern p_and=Pattern.compile(regEx_and,Pattern.CASE_INSENSITIVE); 
        Matcher m_and=p_and.matcher(htmlStr); 
        htmlStr=m_and.replaceAll(""); //����&****;��ǩ

        return htmlStr.trim(); //�����ı��ַ��� 
    }
    
    public static void generate(String piece, String filename) throws JSONException {
		AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
		
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);
		
		System.setProperty("aip.log4j.conf", "log4j.properties");
		TtsResponse res = client.synthesis(piece, "zh", 1, null);
        byte[] data = res.getData();
        JSONObject res1 = res.getResult();
        if (data != null) {
            try {
                Util.writeBytesToFileSystem(data, filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (res1 != null) {
            System.out.println(res1.toString(2));
        }
        
	}
	public static void generateById(int id, String content) throws JSONException {
		//String demo="ժ���������š�������2016��ȫ���߿����в���һ���µ�ʱ�䣬�ڽ��ű�����ʱ��һ���йظ߿������ƻ�����Ϣȴ�ڲ��ֿ����ͼҳ������������ǡ����������յȵصļҳ�����һЩ��У�ر���λ�ڱ�ʡ���ص��У�ڱ��ص�������ģ��С���Դˣ����ؽ������Ž����˻�Ӧ�������������ȷ���֧Ԯ����������������һ����Դ����һ��֪ͨ��4�µף��������� ���ҷ�չ�ĸ�ί��������2016����ͨ�ߵȽ��������ƻ����ƺ͹�������֪ͨ���·�����֪ͨ���ᵽΪ�ٽ��ߵȽ�������ͳ�����ѧ���ṫƽ��2016�֧꣬Ԯ��������������Э���ƻ�����21���ˣ����б���14���ˣ��ɱ�������򡢽��յ�14��ʡ���У��Ĺ�����ͨ��У�е���������ϡ����������ݡ������10��������ʡ�����������������˺󣬽���ʡ����������������2016�꽭�ոߵ�ѧУ��ʡ�����ƻ���˵��������ʾ����ʡ������3.8��������ƻ����ŵ�������ʡ�ݡ����ݺ���������������Ϣ������ʡ��У�������ڶ���ί����У��2016�꽫����4������ƻ�֧��������10��ʡ�������������ҳ����ı���������ģ��С������Щ��Ϣ����һ���ָ߿��������ҳ�����ʡ�������ƻ����١��߿�¼ȡ���½��ĵ��ġ������������������׾����Ϻ������ص��ѧ����ʡԭ������985��ֻ����211��211��ֻ�����ձ��������żٽ���ϰ�࣬��У����ѧ����������Ϊ���Ϻô�ѧ����ҽ��㡣��һЩ�ҳ��ƣ�����ӵ����������б�Ľ������ߣ���ϣ���йز�����ƽ�������֮��Ĺ�ϵ�����ϸ߿�������ƽ���ܽ���Ȩ�������ܽ��պͺ����Ľ������Ŵ�ǰ����ȷ��ʾ��֧Ԯ������ʡ�ݵ������ƻ�������Ӱ�챾�������ƻ��������ƺ������ܻ���ҳ��Ϳ����ǶԱ��ء����С��ĵ��ǡ��������ա������������Ż�Ӧ�������ա����Դˣ����ա��������������գ�13�գ��������Ⱥ�����˻�Ӧ�����ս������ٷ�΢��@���ս��������ԡ����桱����ʽ�����˻�Ӧ������ƾ���ʾ����������������ɹ���ר��ƻ���ͬʱ��ȷ��2016����ͨ��У��ר�������ƻ������ս���ѧ�����ܹ�ģ������ȥ�꣬ȷ�����Ƹ��������ս���ѧ���ļƻ���ģ��������ȥ�ꡣ��������ʡ�����������򽡽���������ܽ���ʡ�㲥������̨ר�ã��͸߿������С��������Ӧ���ڹ��С�����һ���߿����漰ÿһ����ͥ��������Ĵ��£������������ͼҳ��Ը߿������ƻ������Ĺ��ĺ͵��ǡ�����������Բ��ָ߿�ѧ���ҳ���������ʡ�߶����ӣ����йط������Э����ȡ���õ�������֧�֣��߿�������֡����С����⡣�������������ܹ����������������١���һ����ͨ��У��ר�������ƻ������ս���ѧ�����ܹ�ģ�����١����Ǳ�һ�����ȸ��������ս���ѧ���ļƻ���ģ���� ���١������ص��У���ս���ѧ�����ܼƻ������١�������ȷ������һ��ȷ�����տ�������¼ȡ�ʽ�һ����ߣ�����ȷ�����տ������ص��У�Ļ������ӣ�����ȷ�� ���տ���Ȩ��õ���Ч���ϡ������ġ���ί���������й���ÿһλ����������ҳ����ģ����������汸��������������������ʡ��������������������������ʵ���ʽ�����˻�Ӧ����Ӧǿ�������������ܹ��������ĸ�������ȥ�ꡱ���ڶ�7��������У�ں����������ƻ�����������ȥ�꣬����¼ȡ�ʲ�����ȥ�꣬һ��¼ȡ�ʾ��Բ�����ȥ�꣬ȫʡ��¼ȡ�ʾ��Բ�����ȥ�ꡣ�������ߣ��ڶ�7��������985����211����У�����ں����������ƻ��Ƿ����٣�������������Ŀǰ������У���ڱ��Ʒ�ʡ�����ƻ��������ǳ�ֹ�ͨЭ�����ڶ�7��������У2016���ں����������ƻ���ȥ�겻���١����ǻ���������¼ȡ�ڼ䣬��ȡ������׷�������ƻ����������ߣ�����Ҫ�е����տ�ʡ��Դ�ƻ�����ʡ�����Ƹ�У�����ں����������ƻ��Ƿ����٣�������������������١�ʡ����У2016���ں����ı��������ܼƻ�������ȥ�꣬���У����챾�Ƹ�У�ں����������ƻ�Ҳ������ȥ�ꡣ�������ߣ����ܲ��ܶԽ�����������������������жϣ�������������ʡίʡ����һֱ�߶����Ӹ�У�����������������ǳ�֧����ʡ�������������ǽ����ܹ��������ĸ�������ȥ�ꡱ���ڶ�7��������У�ں����������ƻ�����������ȥ�꣬����¼ȡ�ʲ�����ȥ�꣬һ��¼ȡ�ʾ��Բ�����ȥ�꣬ȫʡ��¼ȡ�ʾ��Բ�����ȥ�ꡣ�������ߣ�����Ϊ�����������Կ����ͼҳ���ʲôףԸ�����������������Ƕ��ǹ����ˣ����Լ��μӹ��߿����ҵĺ���Ҳ�μӹ��߿�����ˣ�����������ͼҳ������顣��ί���������й���ÿ������������ͺ�����������ÿλ������������ϣ��ÿλ������ȫ��Ͷ�룬���汸��������ףԸÿλ���������彡��������˳����";
		int i=0;
		String u=System.getProperty("webapp.root");
		String content_removedHTML = delHTMLTag(content);
		if (content_removedHTML.length()>500) {
			List<String> pieces = getStrList(content_removedHTML, 500);
			for (String piece : pieces) {
				generate(piece, u+"//Audios//TMP//"+id+"_"+i+".mp3");
				System.out.println((i++)+1+" of "+pieces.size()+" Generated");
			}
			//TODO: Merge!
			try {
				MergeAudio.mergeFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			generate(content_removedHTML, u+"//Audios//"+id+".mp3");
		}
	}
	public void run() {
		try {
			generateById(id, content);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
