package com.chengsy.code.tools;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author chengsiyi
 * @date 2020/11/27 13:51
 */
public class TextProcess {

    Set<Integer> set ;

    public static void main(String[] args) {
        //第一场预出奖用户：
        List<Integer> preDrawUser = Arrays.asList(135587,72069,115255,127853,83717,118486,117604,115009,135963,65182,136574,134844,137706,123223,120238,130384,137476,136631,73714,137066,135179,134469,70696,137535,121657,137261,69780,137592,116307,106886,137000,131921,136240,129425,77034,29764,133848,124716,136930,126024,137009,118672,137711,114580,134470,67718,135894,137102,74714,137641,81510,116874,122401,122291,89425,137794,136913,47258,136789,53170,137588,137381,135988,137858,123037,115203,135840,72093,136747,136810,137424,136064,68433,136677,137825,134546,135355,136428,114393,120042,123654,30,53795,137204,114181,116170,129412,137052,88326,125014,137483,77859,77797,137422,136456,137730,134351,47879,130095,133504,88102,133402,47011,137607,137133,97446,77306,128551,116888,136684,79686,1669,115397,114728,112700,137161,113762,96668,136966,137146,65020,129471,135736,137805,116703,71411,137738,135212,137334,130815,125109,137734,115179,137388,96747,133805,134093,135082,137404,105944,123993,92979,132447,132058,122397,68962,6111,77230,121454,135920,137046,136050,131518,122487,137783,135381,132629,137591,123769,135167,113144,90462,122406,114610,118572,48121,134415,95739,95150,137695,115222,137656,135216,114960,136637,137834,32,135930,65144,77025,88799,132486,71423,136511,61706,123132,135827,126370,112783,116018,76708,130610,134915,136055,60754,45700,118415,136708,118029,134682,114583,137086,137459,136896,137541,136448,128047,115207,137282,137653,122157,124918,121912,115772,64157,135899,136179,69736,135419,2,107254,84031,80196,137289,137807,72660,129050,135535,89141,74018,123863,132799,75459,115286,68678,113368,62260,136948,135531,187,135732,107094,133647,137833,92480,123106,126661,135187,132812,125860,137795,136326,134848,137544,89537,77193,135602,136073,65048,137768,122214,127236,81705,132422,107012,26100,124937,136454,137838,137810,134298,133499,76608,137620,137357,137499,76318,121239,120117,136746,123149,118528,135316,78349,128917,89812,135171,137198,113569,135039,136902,115403,121159,125321,133807,83826,70380,134207,137754,132369,130492,94447,133634,134041,74159,121727,137850,116415,135117,131825,117258,135462,76032,67500,47410,134663,123610,66812,135211,134524,121744,127844,135805,127905,137513,5458,115902,137799,137828,130644,137816,77814,137065,131707,137663,127083,65950,119559,25889,137429,137750,128714,79478,96660,123564,137028,136444,115791,137697,134698,137082,121757,123040,113069,135348,87362,136957,132966,122285,122573,121423,137124,125170,136989,64222,32596,137739,62727,137758,125231,120696,118622,80090,137286,132161,44662,137417,58779,115495,100522,45670,44512,74306,30649,135030,137587,137376,137575,136613,135829,137740,137431,76024,131892,133947,112446,133453,137608,6071,65233,76842,22,121779,137792,113448,114691,137854,133137,131772,120677,137573,117873,133183,66152,137784,121457,98977,48080,116788,136949,119397,137820,115257,112926,68170,69802,124224,120822,115027,119960,136906,58849,76945,90210,136352,125176,137831,62709,122721,82329,137781,123630,123368,128820,69018,134939,137666,45809,134858,70372,65167,137398,136114,134992,137770,76901,133658,78693,137764,137330,137635,124583,80902,136338,137126,123583,135795,121466,137726,137576,137788,125111,124408,136905,137777,53768,112999,135778,137561,120975,115267,137756,136315,95440,137293,134613,125019,114831,114976,121460,137733,134574,134751,79957,30113,6347,133659,137216,137826,117048,96922,137773,124549,79590,135422,137626,137068,137301,32453,137083,136035,135803,66670,137211,137665,136800,45663,82227,76104,113380,18464,80827,137529,80624,113322,137704,136196,133556,91455,109348,137594,134026,126000,79548,130517,132756,119148,23859,24297,119312,135584,137629,136559,81310,137323,137477,112925,127322,111269,97669,116743,131917,137180,137461,68304,91158,87647,54613,133660,137864,134712,131594,137688,114269,131593,135780,85850,133458,135543,120913,83985,131487,137223,135000,136312,137255,134787,64972,137801,114829,74830,134993,137765,134568,121425,115335,136998,137060,135554,84074,133558,137749,136322,135401,136565,136923,119092,122097,74127,137087,137548,137676,132955,132794,137443,135687,137853,131612,134873

        );
        List<Integer> drawResultUser = Arrays.asList(4658,115302,133296,137224,122885,87647,113670,95440,126173,81550,137404,135381,136565,30381,136396,132486,69287,21,131892,81918,121776,135422,90210,117550,113049,126385,121239,114829,135587,125773,45,132307,128000,137029,71315,7476,132528,129614,121460,72312,115286,116703,95596,118029,116257,137481,134921,114181,65020,58731,32453,127322,115812,65737,125209,137082,66553,121423,137816,125235,121086,123564,90468,74306,116874,136361,9628,112997,65144,136444,115192,65233,47879,89872,82303,115267,131917,62238,135580,137301,134524,81846,67442,115300,115208,126370,87029,84074,75860,83440,118724,137512,53795,85797,125321,136326,129412,30,136433,134915,133453,72069,117604,69273,134415,81478,116170,131707,117268,115222,136179,47411,127220,53768,133947,66152,77797,99400,97446,136448,114535,34935,45809,77193,130644,135030,44765,187,32596,65048,123149,130095,132369,68283,131593,137431,136684,120677,134844,134698,114269,116754,136050,121779,135171,136559,94447,137833,132812,135419,133807,134129,115772,54613,107012,135827,112999,137388,136613,136338,78349,127844,129471,134546,92979,124224,135348,98977,137000,135554,75459,135736,115495,118672,136322,6111,65167,123223,115255,121466,69549,89812,74830,53170,47258,132447,137161,122214,137726,136454,136677,137588,137573,66670,79548,135584,89425,114728,136746,132895,76901,117258,74018,135778,137665,115428,3,28545,135433,125176,133402,76068,137362,132963,116338,125231,136040,132625,129256,7895,137017,68067,81510,137022,126024,89537,61030,116855,116647,88102,135359,80196,115494,89940,113203,136777,116638,60754,127881,76708,73847,115009,112973,125014,77306,122736,137028,135732,107254,135930,120042,137739,137513,91413,77148,128714,71423,134568,62709,64277,135894,112580,137476,133504,136930,137286,137575,64972,134198,25889,135988,109348,127083,114691,134469,115836,44662,91158,6071,76812,137270,133805,121657,68962,113086,121912,127236,133634,79478,115902,137086,113448,133660,2,137009,114831,105944,114583,76104,84031,90462,79877,119092,32,128551,134351,73714,132966,113069,60196,77034,76945,5458,123630,30113,134993,64157,82227,77814,127853,29764,137499,136637,130384,135117,135602,132422,137594,113762,137124,137146,121757,96747,22,76842,115397,134858,136949,128820,134992,96668,58779,124937,115403,118415,96660,76032,122721,137730,70372,137068,137334,132756,133848,131518,121454,118622,68170,120238,26100,130610,114960,136906,114976,135167,135829,71411,92480,137424,113144,135543,132799,125019,137794,114393,78693,115027,136998,120811,132459,131788,134682,137037,132432,119864,135352,136401,127065,116402,136544,136356,95150,131177,123654,122853,134687,123020,115791,114738,123368,26870,7497,126628,88799,80147,136955,124716,135957,135000,76007,114250,112446,128155,120913,122397,123610,136073,88301,87610,135805,137561,137198,134207,113368,82329,137429,73318,116018,136362,136787,131487,131921,136315,136064,80090,45603,137052,78200,77025,124084,137535,87362,116307,134424,81310,136574,94875,135899,123769,114347,136708,137629,126462,137459,107094,137102,88326,136789,133499,119799,137066,74127,119559,125111,123132,125109,137483,136055,136456,64222,127905,112700,137727,136035,134430,70696,137768,128047,62727,45670,123106,123040,133658,137204,135039,81705,136896,135179,76608,132629,136631,45700,136810,69736,123863,68678,121727,47011,58849,68433,111269,74714,121425,106886,137282,135462,115257,89141,113380,122285,74818,131772,67914,122157,1669,113446,68178,119218,136634,136511,8663,112783,80624,133137,137711,115203,135840,137357,67500,132161,134613,122487,136428,130815,135920,132058,48121,44512,137461,115179,137376,134751,135316,77230,118572,129050,122406,137626,117048,135531,133647,133556,126661,114580,137607,135355,137828,45898,137591,122401,80902,71086,83717,30649,134093,131594,65950,122843,134848,100522,124918,122291,123779,116749,48080,114569,69018,136352,120696,134470,79590,79686,115207,136240,137697,137641,67718,129667,125860,76024,76318,135212,133659,120822,129425,137663,134298,136114,136902,135780,116888,136966,123993,133458,137046,131825,135082,124583,122573,83826,123037,120117,135795,80827,135535,118486,128917,118528,69780,95739,91455,136913,114610,79957,137529,65182,134041,133183,121457,112926,70380,137688,134712,122097,113569,72093,119397,116415,137289,134574,137330,123583,137381,62260,135216,116788,47410,134026,119312,130492,134663,6347,69802,136923,124408,119960,72660,96922,121744,136905,45663,130517,134787,115335,18464,134939,121159,136789,135803,112925,136196,124549,135401

        );

        List<Integer> notDrawUser = preDrawUser.stream().distinct().filter(f -> !drawResultUser.contains(f)).collect(Collectors.toList());
        System.out.printf("预出奖了，但是没有领奖用户:人数%d, 详情：%s\n", notDrawUser.size(),notDrawUser);

        List<Integer> justDraw = Arrays.asList(69183,135187,77859,83985,132794,113970,120665, 136312,137216, 137060,85850,137859,134763,129450,137126,125170,65316,70143,77087,116425,121049,137765,6092,122917,137617, 77700, 38800,137211,133768,77822,67655,61706,132952,126836,131612,120097,115265,136747,116743,120975,78222,68304,119250,132955,124994,123337,77574,113322,124587,130517,133558,137749,131947,134873,2521,119148,23859,123432,24297
        );
        List<Integer> missDraw = notDrawUser.stream().distinct().filter(f -> justDraw.contains(f)).collect(Collectors.toList());
        System.out.printf("没有领奖用户,是在福利雨下架后请求的用户:人数%d, 详情：%s",missDraw.size(), missDraw);


    }
}