package dev.mvc.product;

import java.util.HashMap;
import java.util.List;

import dev.mvc.cloth.ClothVO;
import dev.mvc.tmember.MemberVO;

public interface ProductDAOInter {

  /** 
   *  데이터를 등록합니다.
   *   <insert id="create" parameterType="ProductVO">
   *   @param productVo 등록할 데이터 객체
   *   @return 등록된 레코드 갯수
   */
  public int create(ProductVO productVo);
  
  
  /**
   *  회원 목록 전체를 출력합니다
   *  <select id="list" resultType="ProductVO">
   *  @param
   *  @return ProductVO 회원 목록
   */
  public List<ProductVO> list();
  
  
  /**  
   * 글 조회.
   * <select id="read" resultType="ProductVO" parameterType="int">
   * @param
   * @return
   */
    public ProductVO read(int pno);
    
    /**
     *   <update id ="update" parameterType="ProductVO">
     *   
     */
    public int update(ProductVO vo);
    
    /**
     * 글을 삭제 합니다.
     *  <delete id="delete" parameterType="int">
     */
    public int delete(int pno);
    
    /**
     *  <update id = "increaseCnt" parameterType = "int" >
     * @param pno
     * @return
     */
    public int increaseCnt(int pno);
    
    /**
     * 검색 목록
     * <select id="list2" resultType="ProductVO" parameterType="HashMap" > 
     * @param hashmap 검색 조건
     * @return
     */
    public List<ProductVO> list2(HashMap hashmap);
    
    /**
     * 검색된 레코드 수
     * <select id="count" resultType="int" parameterType="HashMap" > 
     * @param hashmap 검색 조건
     * @return
     */
    public int count(HashMap hashmap);
    
    
    /**
     * 검색 목록
     * <select id="list3" resultType="ProductVO" parameterType="HashMap" > 
     * @param hashmap 검색 조건
     * @return
     */
    public List<ProductVO> list3(HashMap hashmap);


    /**
     * 회원정보(닉네임, 이메일) 가져오기
     * @param userid
     * @return
     */
    public MemberVO test(String userid);
}
