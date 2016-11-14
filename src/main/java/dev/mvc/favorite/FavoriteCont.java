package dev.mvc.favorite;

import java.awt.Image;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import web.tool.Paging;
import web.tool.SearchDTO;
import web.tool.Tool;

@Controller
public class FavoriteCont {
  @Autowired
  @Qualifier("dev.mvc.favorite.FavoriteDAO")
  private FavoriteDAOInter favoriteDAO;
  
  public FavoriteCont(){
    System.out.println("--> FavoriteCont created.");
  }

     /** ��� ��
     *   
     * @param f_no
     * @return
     * @throws IOException 
     */
      @RequestMapping(value = "/favorite/create.do", 
          method = RequestMethod.GET)
    public void create(HttpSession session, HttpServletResponse response, HttpServletRequest request,
        String nickname, String title, int hprice, String url, String thumb, String locat) throws IOException { 
          System.out.println(hprice);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
     
     
       
      String[] split = url.split("/");
      String thumb_url = split[0]+"//"+split[1]+split[2]+"/"+split[3]+"/"+split[4]+"/"+ "storage/"+thumb.trim();
      String userid = session.getAttribute("userid").toString();
      FavoriteVO favoriteVO=new FavoriteVO();
      favoriteVO.setUserid(userid.trim());
      favoriteVO.setNickname(nickname.trim());
      favoriteVO.setTitle(title.trim());
      favoriteVO.setUrl(url.trim());
      favoriteVO.sethprice(hprice);
      favoriteVO.setThumb(thumb_url);
      System.out.println(url);
      System.out.println(split[0]+"//"+split[1]+split[2]+"/"+split[3]+"/"+split[4]+"/"+ "storage/"+thumb.trim());
      System.out.println("�׸����Ͷ� "+thumb);
   /*   URL url1 = new URL(loca);
      Image image =ImageIO.read(url1);
      */
     // System.out.println(image);

      List<FavoriteVO> list = favoriteDAO.list_userid(userid); // �˻�
      Iterator<FavoriteVO> iter = list.iterator();
      int cnt = 0;
      while (iter.hasNext() == true) { // ���� ��� �˻�
        FavoriteVO vo = iter.next(); // ��� ����
        System.out.println("vo.getUrl(): "+ vo.getUrl());
        if (favoriteVO.getUrl().equals(vo.getUrl())) {
          cnt++;
        } 
      }
        if(cnt == 0) {
        if (favoriteDAO.create(favoriteVO) == 1) {
                  PrintWriter writer = response.getWriter();
                  writer.println
                  ("<script>alert('�߰��Ǿ����ϴ�');" 
                   + "location.href = './list.do?userid=" +userid + "';"
                   + "</script>"
                      );
                } else {
                PrintWriter writer = response.getWriter();
                writer.println
                ("<script>alert('�߰��� ���� ������ �ּ���');" 
                 + "location.href = './list.do?userid=" + userid + "';"
                 + "</script>"
                    );
                }
          } else {
            PrintWriter writer = response.getWriter();
            writer.println
            ("<script>alert('�̹� �����մϴ�');" 
             + "location.href = './list.do?userid=" + userid + "';"
             + "</script>"
                );
          }
        }
     

  
  
 
  /**
   * u_no ���� �Խ��� ����� �˻�+����¡�Ͽ� ����մϴ�.
   * 
   * @param u_no
   *          ��ü ��Ͽ��� ������ �Խ��� ��ȣ
   * @param searchDTO �˻� ����         
   * @return ����� �Խ��� ���
   */
  @RequestMapping(value = "/favorite/list.do", 
                             method = RequestMethod.GET)
  public ModelAndView list(HttpSession session, SearchDTO searchDTO,
                                        HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/favorite/list");
    int totalRecord = 0;
    
    
    // HashMap hashMap = new HashMap();
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("col", searchDTO.getCol());
    hashMap.put("word", searchDTO.getWord());
    hashMap.put("userid",session.getAttribute("userid").toString());
    
    int recordPerPage = 10; // �������� ����� ���ڵ� ����
    // ���������� ����� ���� ���ڵ� ��ȣ ���, nowPage�� 1���� ����
    int beginOfPage = (searchDTO.getNowPage() - 1) * 10; 
    // 1 page: 0
    // 2 page: 10
    // 3 page: 20
    int startNum = beginOfPage + 1; // ���� rownum, 1
    int endNum = beginOfPage + recordPerPage; // ���� rownum, 10
    hashMap.put("startNum", startNum);
    hashMap.put("endNum", endNum);

    List<FavoriteVO> list = favoriteDAO.list(hashMap); // �˻�
    Iterator<FavoriteVO> iter = list.iterator();
    while (iter.hasNext() == true) { // ���� ��� �˻�
      FavoriteVO vo = iter.next(); // ��� ����
      vo.setTitle(Tool.textLength(vo.getTitle(), 10));
      // vo.setFile1(Tool.textLength(10, vo.getFile1()));
      // vo.setFile2(Tool.textLength(10, vo.getFile2()));
    }
    mav.addObject("list", list);
    
    totalRecord = favoriteDAO.count(hashMap);
    mav.addObject("totalRecord", favoriteDAO.count(hashMap)); // �˻��� ���ڵ� ����

    String paging = new Paging().paging5(
                                           totalRecord, 
                                           searchDTO.getNowPage(), 
                                           recordPerPage, 
                                           searchDTO.getCol(), 
                                           searchDTO.getWord());
    mav.addObject("paging", paging);
    return mav;
  }
  
 /**
  * ���ø���Ʈ ��� ���� ���� 
  * @param favoriteVO
  * @param response
  * @param session
  * @param f_no_arr
  * @throws IOException
  */
  @RequestMapping(value = "/favorite/visible.do", method = RequestMethod.GET)
  public void visible(FavoriteVO favoriteVO, HttpServletResponse response, HttpSession session
      , @RequestParam List<String> f_no_arr) throws IOException {
    System.out.println("��Ʈ�ѷ�:"+ f_no_arr);
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
   
    HashMap<String, Object> hashMap = new HashMap<String, Object>();
    hashMap.put("f_no_arr", f_no_arr);
    hashMap.put("userid", session.getAttribute("userid"));
    String userid = session.getAttribute("userid").toString();
    
    int sendOK = favoriteDAO.visible(hashMap);
    System.out.println(sendOK);
    if (sendOK != 0) {
      PrintWriter writer = response.getWriter();
      writer.println
      ("<script>alert('�����Ǿ����ϴ�');" 
       + "location.href = './list.do?userid=" +userid + "';"
       + "</script>"
          );
  } else {
    PrintWriter writer = response.getWriter();
    writer.println
    ("<script>alert('������ ���� ������ �ּ���');" 
     + "location.href = './list.do?userid=" + userid + "';"
     + "</script>"
        );
  }
  return;
  }
}
